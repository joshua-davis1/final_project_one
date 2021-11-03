package com.ss.lms.dao;

import com.ss.lms.entity.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseDAO<Genre> {
    public GenreDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Genre> genres = new ArrayList<>();
        while (rs.next()) {
            Genre genre = new Genre();
            genre.setGenreId(rs.getInt("genreId"));
            genre.setGenreName(rs.getString("genreName"));
            genres.add(genre);
        }
        return genres;
    }

    public List<Genre> readGenres() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_genre", null);
    }

    public void addGenre(Genre genre) throws SQLException {
        save("INSERT INTO tbl_genre (genreName) VALUES (?)",
                new Object[] { genre.getGenreName() });
    }

    public void updateGenre(Genre genre) throws SQLException {
        save("UPDATE tbl_genre SET genreName = ? WHERE genreId = ?",
                new Object[] { genre.getGenreName(), genre.getGenreId() });
    }

    public void deleteGenre(Genre genre) throws SQLException {
        save("DELETE FROM tbl_genre WHERE genreId = ?",
                new Object[] { genre.getGenreId() });
    }
}
