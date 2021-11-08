package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDOA extends BaseDAO<Stock> {
    public StockDOA(Connection conn) {
        super(conn);
    }

    public List<Stock> readStock() throws SQLException, ClassNotFoundException {
        // branchId + bookId + noOfCopies
        return read("SELECT * FROM tbl_book_copies", null);
    }

    public void addStock(Stock stock) throws SQLException {
        save("INSERT INTO tbl_book_copies(bookId, branchId, noOfCopies) VALUES(?, ?, ?)",
                new Object[] {
                        stock.getBook().getId(),
                        stock.getBranchId(),
                        stock.getQuantity()
                });
    }

    public void updateStockQty(Stock stock) throws SQLException {
        save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?", new Object[] {
                stock.getQuantity(),
                stock.getBook().getId(),
                stock.getBranchId()
        });
    }

    public void deleteStock(Stock stock) throws SQLException {
        save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
                new Object[] {
                        stock.getBook().getId(),
                        stock.getBranchId()
                });
    }

    @Override
    protected List<Stock> extractData(ResultSet rs) throws SQLException {
        List<Stock> inventory = new ArrayList<>();
        while (rs.next()) {
            Stock stock = new Stock(
                    rs.getInt("branchId"),
                    new Book(rs.getInt("bookId")),
                    rs.getInt("noOfCopies"));
            inventory.add(stock);
        }
        return inventory;
    }
}
