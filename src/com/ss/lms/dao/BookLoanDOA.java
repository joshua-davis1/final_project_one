package com.ss.lms.dao;

import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLoanDOA extends BaseDAO<BookLoans> {
    public BookLoanDOA(Connection conn) {
        super(conn);
    }

    @Override
    protected List<BookLoans> extractData(ResultSet rs) throws SQLException {
        List<BookLoans> bookLoans = new ArrayList<>();
        while (rs.next()) {
            BookLoans bloan = new BookLoans();
            bloan.getBook().setBookId(rs.getInt("bookId"));
            bloan.getLibraryBranch().setBranchId(rs.getInt("branchId"));
            bloan.getBorrower().setCardNo(rs.getInt("cardNo"));
            bloan.setDateOut(rs.getDate("dateOut"));
            bloan.setDateIn(rs.getDate("dateIn"));
            bloan.setDueDate(rs.getDate("dueDate"));
            bookLoans.add(bloan);
        }
        return bookLoans;
    }

    public List<BookLoans> readBookLoans() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_loans", null);
    }

    public void addBookLoans(BookLoans bookLoan) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dateIn, dueDate ) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[] { bookLoan.getBook().getBookId(), bookLoan.getLibraryBranch().getBranchId(), bookLoan.getBorrower().getCardNo(), bookLoan.getDateOut(), bookLoan.getDateIn(), bookLoan.getDueDate() });
    }

    public void checkOutBook(BookLoans bookLoan) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book_loans SET dateOut = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] { bookLoan.getDateOut(), bookLoan.getBook().getBookId(), bookLoan.getLibraryBranch().getBranchId(), bookLoan.getBorrower().getCardNo() });
    }

    public void deleteBookLoans(BookLoans bookLoan) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_book_loans WHERE  bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] { bookLoan.getBook().getBookId(), bookLoan.getLibraryBranch().getBranchId(), bookLoan.getBorrower().getCardNo() });
    }
}
