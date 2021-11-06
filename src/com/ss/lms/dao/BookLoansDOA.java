package com.ss.lms.dao;

import com.ss.lms.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLoansDOA extends BaseDAO<BookLoans> {

    public BookLoansDOA(Connection conn) {
        super(conn);
    }

    @Override
    protected List<BookLoans> extractData(ResultSet rs) throws SQLException {
        List<BookLoans> loans = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book(rs.getInt("bookId"));
            Branch branch = new Branch(rs.getInt("branchId"));
            Borrower borrower = new Borrower(rs.getInt("cardNo"));
            BookLoans bookLoans = new BookLoans(book, branch, borrower, rs.getDate("dateOut").toLocalDate(), rs.getDate("dueDate").toLocalDate());
            if(rs.getDate("dateIn") != null) bookLoans.setDateIn(rs.getDate("dateIn").toLocalDate());
            loans.add(bookLoans);
        }
        return loans;
    }

    public List<BookLoans> readBookLoans() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_loans", null);
    }

    public void addBookLoan(BookLoans bookLoans) throws SQLException {
        save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dateIn, dueDate) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[] {bookLoans.getBook().getBookId(), bookLoans.getBranch().getBranchId(), bookLoans.getBorrower().getCardNo(), bookLoans.getDateOut(), bookLoans.getDateIn(), bookLoans.getDueDate()});
    }
}
