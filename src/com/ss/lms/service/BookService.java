package com.ss.lms.service;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final ConnectionUtil connUtil;

    public BookService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Book> getAllBooks() throws SQLException {
        Connection conn = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);
            books = bdao.readBooks();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return books;
    }

    public void addBook(Book book) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);
            bdao.addBook(book);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }
}
