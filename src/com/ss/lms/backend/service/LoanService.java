package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.LoanDAO;
import com.ss.lms.backend.entity.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanService {

    private final ConnectionUtil connUtil;

    public LoanService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public void updateLoan(Loan loan) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LoanDAO loanDAO = new LoanDAO(conn);
            loanDAO.updateLoan(loan);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: addLoan");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void addLoan(Loan loan) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LoanDAO loanDAO = new LoanDAO(conn);
            loanDAO.addLoan(loan);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: addLoan");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public Boolean loanExist(Loan loan) throws SQLException {
        Boolean rs = false;
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LoanDAO loanDAO = new LoanDAO(conn);
            return loanDAO.loanExist(loan);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: loanExist");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return rs;
    }

    public Loan findLoan(Loan loan) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LoanDAO loanDAO = new LoanDAO(conn);
            return loanDAO.findLoan(loan);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: findLoan");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return loan;
    }

    public List<Loan> getLoans() throws SQLException {
        Connection conn = null;
        List<Loan> loans = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            LoanDAO loanDAO = new LoanDAO(conn);
            loans = loanDAO.readLoans();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getLoans");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return loans;
    }



}
