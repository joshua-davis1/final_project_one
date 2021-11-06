package com.ss.lms.service;

import com.ss.lms.dao.BookLoansDOA;
import com.ss.lms.entity.BookLoans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLoansService {

    private final ConnectionUtil connUtil;

    public BookLoansService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<BookLoans> getAllBookLoans() throws SQLException {
        Connection conn = null;
        List<BookLoans> bookLoansList = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BookLoansDOA bldoa = new BookLoansDOA(conn);
            bookLoansList = bldoa.readBookLoans();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return bookLoansList;
    }


    public void addBookLoan(BookLoans bookLoans) throws SQLException {
        Connection conn = null;
        String rs;
        try {
            conn = connUtil.getConnection();
            BookLoansDOA bldao = new BookLoansDOA(conn);
            bldao.addBookLoan(bookLoans);
            conn.commit();
            System.out.println("Branch updated successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }


}
