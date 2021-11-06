package com.ss.lms.dao;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Borrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO extends BaseDAO<Borrower> {
    public BorrowerDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Borrower> borrowers = new ArrayList<>();
        while (rs.next()) {
            Borrower borrower = new Borrower(rs.getInt("cardNo"), rs.getString("name"), rs.getString("address"), rs.getString("phone"));
            borrowers.add(borrower);
        }
        return borrowers;
    }

    public List<Borrower> readBorrowers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_borrower", null);
    }
}
