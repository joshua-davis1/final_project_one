package com.ss.lms.backend.service;

import com.ss.lms.backend.dao.GenreDAO;
import com.ss.lms.backend.entity.Genre;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenreService {

    private final ConnectionUtil connUtil;

    public GenreService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Genre> getGenres() {
        try (Connection conn = connUtil.getConnection()) {
            GenreDAO genreDAO = new GenreDAO(conn);
            return genreDAO.readGenres();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: getGenres");
            e.printStackTrace();
        }
        return null;
    }

    public void addGenre(String genreName) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            GenreDAO genreDAO = new GenreDAO(conn);
            genreDAO.addGenre(genreName);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: addGenre");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void updateGenre(Genre genre) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            GenreDAO genreDAO = new GenreDAO(conn);
            genreDAO.updateGenre(genre);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: updateGenre");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void deleteGenre(Genre genre) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            GenreDAO genreDAO = new GenreDAO(conn);
            genreDAO.deleteGenre(genre);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql error: deleteGenre");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
    }
}
