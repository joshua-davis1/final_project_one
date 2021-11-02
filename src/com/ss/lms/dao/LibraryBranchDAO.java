package com.ss.lms.dao;

import com.ss.lms.entity.LibraryBranch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
    public LibraryBranchDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<LibraryBranch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }

    public List<LibraryBranch> readLibraryBranches() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_library_branch", null);
    }

    public void addLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?, ?)",
                new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
    }

    public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book SET branchName = ? AND branchAddress = ? WHERE branchId =?",
                new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress(), libraryBranch.getBranchId()});
    }

    public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_book WHERE bookId = ?",
                new Object[] {libraryBranch.getBranchId()});
    }

}
