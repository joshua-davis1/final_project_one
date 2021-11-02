package com.ss.lms.dao;

import com.ss.lms.entity.BookCopies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCopiesDAO extends BaseDAO<BookCopies> {
    public BookCopiesDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<BookCopies> extractData(ResultSet rs) throws SQLException {
        List<BookCopies> bookCopies = new ArrayList<>();
        while (rs.next()) {
            BookCopies bc = new BookCopies();
            bc.getBook().setBookId(rs.getInt("bookId"));
            bc.getLibraryBranch().setBranchId(rs.getInt("branchId"));
            bc.setCopies(rs.getInt("noOfCopies"));
            bookCopies.add(bc);
        }
        return bookCopies;
    }

    public List<BookCopies> readBookCopies() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_copies", null);
    }

    public void addBookCopies(BookCopies bookCopies) throws SQLException {
        save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?)",
                new Object[] { bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId(), bookCopies.getCopies()});
    }

    public void updateBookCopies(BookCopies bookCopies) throws SQLException {
        save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?)",
                new Object[] { bookCopies.getCopies(), bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId()});
    }
    public void deleteBookCopies(BookCopies bookCopies) throws SQLException {
        save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
                new Object[] { bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId()});
    }

}
