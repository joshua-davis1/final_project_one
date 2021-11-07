package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.BorrowerDAO;
import com.ss.lms.backend.entity.Borrower;
import com.ss.lms.backend.entity.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerService {

    private final ConnectionUtil connUtil;

    public BorrowerService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public Borrower getBorrowerByCardNo(int cardNo) {
        Connection conn = null;
        Borrower borrower = null;
        try {
            conn = connUtil.getConnection();
            BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
            borrower = borrowerDAO.getBorrowerByCardNo(cardNo);
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getBorrowerByCardNo");
            e.printStackTrace();
        }
        return borrower;
    }

    public List<Borrower> getBorrowers() throws SQLException {
        Connection conn = null;
        List<Borrower> borrowerList = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
            borrowerList = borrowerDAO.readBorrowers();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getBooks");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return borrowerList;
    }
}
