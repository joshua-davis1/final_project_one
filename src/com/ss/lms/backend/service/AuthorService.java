package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.AuthorDAO;
import com.ss.lms.backend.entity.Author;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

    private final ConnectionUtil connUtil;

    public AuthorService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Author> getAuthors() throws SQLException {
        Connection conn = null;
        List<Author> authors = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            AuthorDAO authorDAO = new AuthorDAO(conn);
            authors = authorDAO.readAuthors();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getAuthors");
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return authors;
    }

}
