package com.ss.lms.service;



import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.entity.Borrower;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerService {
    private final ConnectionUtil connUtil;

    public BorrowerService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Borrower> getAllBorrowers() throws SQLException {
        Connection conn = null;
        List<Borrower> borrowers = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BorrowerDAO adao = new BorrowerDAO(conn);
            borrowers = adao.readBorrowers();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return borrowers;
    }
}
