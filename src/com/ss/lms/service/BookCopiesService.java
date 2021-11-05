package com.ss.lms.service;



import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.entity.BookCopies;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCopiesService {

    private final ConnectionUtil connUtil;

    public BookCopiesService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<BookCopies> getAllBookCopies() throws SQLException {
        Connection conn = null;
        List<BookCopies> inventory = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BookCopiesDAO bcdao = new BookCopiesDAO(conn);
            inventory = bcdao.readBookCopies();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return inventory;
    }

    public void updateInventory(BookCopies stock) throws SQLException {
        Connection conn = null;
        String rs;
        try {
            conn = connUtil.getConnection();
            BookCopiesDAO bcdao = new BookCopiesDAO(conn);
            bcdao.updateInventory(stock);
            conn.commit();
            System.out.println("Branch updated successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }
}
