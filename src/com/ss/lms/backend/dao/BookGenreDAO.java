package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.BookGenre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDAO extends BaseDAO<BookGenre>{

    public BookGenreDAO(Connection conn) {
        super(conn);
    }

    public List<BookGenre> readBookGenres() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_genres",
                null);
    }

    public void addBookGenre(BookGenre bookGenre) throws SQLException {
        save("INSERT INTO tbl_book_genres(genreId, bookId) VALUES(?, ?)",
                new Object[] {
                        bookGenre.getGenreId(),
                        bookGenre.getBookId()
                });
    }

    public void deleteBookGenre(BookGenre bookGenre) throws SQLException {
        save("DELETE FROM tbl_book_genres WHERE genreId = ? AND bookId = ?",
                new Object[] {
                        bookGenre.getGenreId(),
                        bookGenre.getBookId()
                });
    }

    @Override
    protected List<BookGenre> extractData(ResultSet rs) throws SQLException {
        List<BookGenre> bookGenreList = new ArrayList<>();
        while(rs.next()) {
            bookGenreList.add(new BookGenre(
                    rs.getInt("genreId"),
                    rs.getInt("bookId")
            ));
        }
        return bookGenreList;
    }
}
