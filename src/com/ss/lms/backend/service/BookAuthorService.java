package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.BookAuthorDAO;
import com.ss.lms.backend.entity.BookAuthor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorService {

    private final ConnectionUtil connUtil;

    public BookAuthorService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<BookAuthor> getBookAuthors() throws SQLException {
        Connection conn = null;
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BookAuthorDAO bookAuthorDAO = new BookAuthorDAO(conn);
            bookAuthorList = bookAuthorDAO.readBookAuthor();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getBookAuthors");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return bookAuthorList;
    }
}
