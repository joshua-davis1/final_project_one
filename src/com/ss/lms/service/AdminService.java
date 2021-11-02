package com.ss.lms.service;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookAuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookAuthor;
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
            pdao.addPublisher(pub);
            conn.commit();
            return "Publisher added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Publisher failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addAuthor(Author author) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            adao.addAuthor(author);
            conn.commit();
            return "Author created successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Author failed to be created";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addBookAuthor(BookAuthor bookAuthor) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookAuthorDAO bdao = new BookAuthorDAO(conn);
            bdao.addBookAuthor(bookAuthor);
            conn.commit();
            return "Author added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Author failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }
}
