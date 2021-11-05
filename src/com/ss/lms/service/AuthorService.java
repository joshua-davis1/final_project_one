package com.ss.lms.service;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.entity.Author;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

    private final ConnectionUtil connUtil;

    public AuthorService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Author> getAllAuthors() throws SQLException {
        Connection conn = null;
        List<Author> authors = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            authors = adao.readAuthors();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return authors;
    }
}
