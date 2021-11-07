package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.BookDAO;
import com.ss.lms.backend.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final ConnectionUtil connUtil;

    public BookService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Book> getBooks() throws SQLException {
        Connection conn = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BookDAO bookDAO = new BookDAO(conn);
            books = bookDAO.readBooks();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getBooks");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return books;
    }


}
