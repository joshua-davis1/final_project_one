package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Borrower;
import com.ss.lms.backend.entity.Loan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO extends BaseDAO<Loan> {


    public LoanDAO(Connection conn) {
        super(conn);
    }

    public List<Loan> readLoans() throws SQLException, ClassNotFoundException {
        // BookLoans + Borrower
        return read("SELECT * FROM tbl_book_loans as lo join tbl_borrower as bo on bo.cardNo=lo.cardNo", null);
    }

    public void updateLoan(Loan loan) throws SQLException {
        save("UPDATE tbl_book_loans SET dateOut = ?, dateIn = ?, dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] {
                        loan.getDateOut(),
                        loan.getDateIn(),
                        loan.getDueDate(),
                        loan.getBookId(),
                        loan.getBranchId(),
                        loan.getBorrower().getCardNo(),
                });
    }

    public void addLoan(Loan loan) throws SQLException {
        save("INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dateIn, dueDate) VALUES(?, ?, ?, ?, ?, ?)",
                new Object[] {
                        loan.getBookId(),
                        loan.getBranchId(),
                        loan.getBorrower().getCardNo(),
                        loan.getDateOut(),
                        loan.getDateIn(),
                        loan.getDueDate()
                });
    }

    public Boolean loanExist(Loan loan) throws SQLException, ClassNotFoundException {
        return !(read("SELECT * FROM tbl_book_loans as lo join tbl_borrower as bo on bo.cardNo=lo.cardNo WHERE lo.bookId = ? AND lo.branchId = ? AND lo.cardNo = ?",
                new Object[] {
                        loan.getBookId(),
                        loan.getBranchId(),
                        loan.getBorrower().getCardNo()
                }).isEmpty());
    }

    public Loan findLoan(Loan loan) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_loans as lo join tbl_borrower as bo on bo.cardNo=lo.cardNo WHERE lo.bookId = ? AND lo.branchId = ? AND lo.cardNo = ?",
                new Object[] {
                        loan.getBookId(),
                        loan.getBranchId(),
                        loan.getBorrower().getCardNo()
                }).get(0);
    }

    @Override
    protected List<Loan> extractData(ResultSet rs) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        while (rs.next()) {
            Borrower borrower = new Borrower(
                    rs.getInt("cardNo"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"));
            Loan loan = new Loan(
                    rs.getInt("bookId"),
                    rs.getInt("branchId"),
                    borrower,
                    rs.getDate("dateOut").toLocalDate(),
                    rs.getDate("dueDate").toLocalDate());
            if(rs.getDate("dateIn") != null) loan.setDateIn(rs.getDate("dateIn").toLocalDate());
            loans.add(loan);
        }
        return loans;
    }
}
