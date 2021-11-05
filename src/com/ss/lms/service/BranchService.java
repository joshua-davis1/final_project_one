package com.ss.lms.service;

import com.ss.lms.dao.BranchDAO;
import com.ss.lms.entity.Branch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchService {

    private final ConnectionUtil connUtil;

    public BranchService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Branch> getAllBranches() throws SQLException {
        Connection conn = null;
        List<Branch> branches = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BranchDAO bdao = new BranchDAO(conn);
            branches = bdao.readBranches();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return branches;
    }

    public String updateBranch(Branch branch) throws SQLException {
        Connection conn = null;
        String rs;
        try {
            conn = connUtil.getConnection();
            BranchDAO bdao = new BranchDAO(conn);
            bdao.updateBranch(branch);
            rs = "Branch updated successfully";
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            rs = "something went wrong!";
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return rs;
    }
}
