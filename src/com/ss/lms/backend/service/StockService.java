package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.StockDOA;
import com.ss.lms.backend.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockService {
    private final ConnectionUtil connUtil;

    public StockService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public void updateStockQty(Stock stock) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            StockDOA stockDOA = new StockDOA(conn);
            stockDOA.updateStockQty(stock);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public List<Stock> getStock() throws SQLException {
        Connection conn = null;
        List<Stock> inventory = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            StockDOA bookDAO = new StockDOA(conn);
            inventory = bookDAO.readStock();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getStock");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return inventory;
    }
}
