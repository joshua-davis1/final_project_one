package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Book;
import com.ss.lms.backend.entity.Borrower;
import com.ss.lms.backend.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO extends BaseDAO<Borrower> {
    public BorrowerDAO(Connection conn) {
        super(conn);
    }

    public List<Borrower> readBorrowers() throws SQLException, ClassNotFoundException {
        // Borrower
        return read("SELECT * FROM tbl_borrower", null);
    }

    public Borrower getBorrowerByCardNo(int cardNo) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_borrower WHERE cardNo = ?",
                new Object[]{cardNo}).get(0);
    }

    @Override
    protected List<Borrower> extractData(ResultSet rs) throws SQLException {
        List<Borrower> borrowerList = new ArrayList<>();
        while (rs.next()) {
            Borrower borrower = new Borrower(
                    rs.getInt("cardNo"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone")
            );
            borrowerList.add(borrower);
        }
        return borrowerList;
    }
}
