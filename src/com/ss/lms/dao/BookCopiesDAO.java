package com.ss.lms.dao;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCopiesDAO extends BaseDAO<BookCopies> {

    public BookCopiesDAO(Connection conn) {
        super(conn);
    }

    public List<BookCopies> readBookCopies() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_copies", null);
    }

    public void updateInventory(BookCopies stock) throws SQLException {
        save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE  bookId = ? AND branchId = ?",
                new Object[] {stock.getNoOfCopies(), stock.getBook().getBookId(), stock.getBranch().getBranchId()}
        );
    }

    @Override
    protected List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookCopies> inventory = new ArrayList<>();
        while (rs.next()) {
            inventory.add(new BookCopies(new Book(rs.getInt("bookId")), new Branch(rs.getInt("branchId")), rs.getInt("noOfCopies")));
        }
        return inventory;
    }
}
