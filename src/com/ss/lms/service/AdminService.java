package com.ss.lms.service;

import com.ss.lms.dao.BookDAO;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.SQLException;


public class AdminService {

    ConnectionUtil connUtil = new ConnectionUtil();

    public String addBook(Book book) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);
            bdao.addBook(book);
            conn.commit();
            return "Book added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Book failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addPublisher(Publisher pub) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);
            pdao.addBook(pub);
            conn.commit();
            return "Publisher added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Publisher failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }
}
