package com.ss.lms.dao;

import com.ss.lms.entity.Borrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDOA extends BaseDAO<Borrower> {
    public BorrowerDOA(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Borrower> extractData(ResultSet rs) throws SQLException {
        List<Borrower> borrowers = new ArrayList<>();
        while (rs.next()) {
            Borrower borrower = new Borrower();
            borrower.setCardNo(rs.getInt("cardNo"));
            borrower.setName(rs.getString("name"));
            borrower.setAddress(rs.getString("address"));
            borrower.setPhone(rs.getString("phone"));
            borrowers.add(borrower);
        }
        return borrowers;
    }

    public List<Borrower> readBorrowers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_borrower", null);
    }

    public void addBorrower(Borrower borrower) throws SQLException {
        save("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)",
                new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone()});
    }

    public void updateBorrower(Borrower borrower) throws SQLException {
        save("UPDATE tbl_borrower SET name = ? AND address = ? AND phone = ? where cardNo = ?",
                new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
    }

    public void deleteBorrower(Borrower borrower) throws SQLException {
        save("DELETE FROM tbl_borrower WHERE cardNo = ?",
                new Object[] { borrower.getCardNo() });
    }
}
