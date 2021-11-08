package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Branch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends BaseDAO<Branch> {
    public BranchDAO(Connection conn) {
        super(conn);
    }

    public List<Branch> readBranches() throws SQLException, ClassNotFoundException {
        // branchId + branchName + branchAddress
        return read("SELECT * FROM tbl_library_branch", null);
    }

    public void addBranch(String name, String address) throws SQLException {
        save("INSERT INTO tbl_library_branch(name, address) VALUES(?, ?)",
                new Object[] {
                        name,
                        address
                });
    }

    public void updateBranch(Branch branch) throws SQLException {
        save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?",
                new Object[] {
                        branch.getName(),
                        branch.getAddress(),
                        branch.getId()
                });
    }

    public void deleteBranch(Branch branch) throws SQLException {
        save("DELETE FROM tbl_library_branch WHERE branchId = ?",
                new Object[] {
                        branch.getId()
                });
    }

    @Override
    protected List<Branch> extractData(ResultSet rs) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch branch = new Branch(
                    rs.getInt("branchId"),
                    rs.getString("branchName"),
                    rs.getString("branchAddress")
            );
            branches.add(branch);
        }
        return branches;
    }
}
