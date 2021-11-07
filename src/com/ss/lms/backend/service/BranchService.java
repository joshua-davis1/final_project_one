package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.BranchDAO;
import com.ss.lms.backend.entity.Branch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchService {
    private final ConnectionUtil connUtil;

    public BranchService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public void updateBranch(Branch branch) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BranchDAO branchDAO = new BranchDAO(conn);
            branchDAO.updateBranch(branch);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public List<Branch> getBranches() throws SQLException {
        Connection conn = null;
        List<Branch> branches = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BranchDAO branchDAO = new BranchDAO(conn);
            branches = branchDAO.readBranches();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getBranches");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return branches;
    }
}
