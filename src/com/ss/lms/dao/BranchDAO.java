package com.ss.lms.dao;

import com.ss.lms.entity.Branch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends BaseDAO<Branch>{

    public BranchDAO(Connection conn) {
        super(conn);
    }

    public List<Branch> readBranches() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_library_branch", null);
    }

    public void updateBranch(Branch branch) throws SQLException {
        save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?",
                new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
    }

    @Override
    protected List<Branch> extractData(ResultSet rs) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch branch = new Branch(rs.getInt("branchId"), rs.getString("branchName"),rs.getString("branchAddress"));
            branches.add(branch);
        }
        return branches;
    }


}
