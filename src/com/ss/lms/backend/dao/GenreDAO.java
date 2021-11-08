package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseDAO<Genre> {
    public GenreDAO(Connection conn) {
        super(conn);
    }

    public List<Genre> readGenres() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_genre",
                null);
    }

    public void addGenre(String genreName) throws SQLException {
        save("INSERT INTO tbl_genre(genreName) VALUES(?)",
                new Object[] {genreName});
    }

    public void updateGenre(Genre genre) throws SQLException {
        save("UPDATE tbl_genre SET genreName = ? WHERE genreId = ?",
                new Object[] {
                        genre.getName(),
                        genre.getId()
                });
    }

    public void deleteGenre(Genre genre) throws SQLException {
        save("DELETE FROM tbl_genre WHERE genreId = ?",
                new Object[] {genre.getId()});
    }

    @Override
    protected List<Genre> extractData(ResultSet rs) throws SQLException {
        List<Genre> genreList = new ArrayList<>();
        while(rs.next()){
            genreList.add(new Genre(
                    rs.getInt("genre_id"),
                    rs.getString("genre_name")
            ));
        }
        return genreList;
    }
}
