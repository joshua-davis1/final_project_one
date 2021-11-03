package com.ss.lms.dao;

import com.ss.lms.entity.BookGenre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDAO extends BaseDAO<BookGenre> {
    public BookGenreDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<BookGenre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookGenre> bookGenres = new ArrayList<>();
        while (rs.next()) {
            BookGenre bookGenre = new BookGenre();
            bookGenre.getBook().setBookId(rs.getInt("bookId"));
            bookGenre.getGenre().setGenreId(rs.getInt("genreId"));
            bookGenres.add(bookGenre);
        }
        return bookGenres;
    }

    public List<BookGenre> readBookGenres() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_genres", null);
    }

    public void addBookGenre(BookGenre bookGenre) throws SQLException {
        save("INSERT INTO tbl_book_genres (bookId, genreId) VALUES (?, ?)",
                new Object[] { bookGenre.getBook().getBookId(), bookGenre.getGenre().getGenreId() });
    }

    public void updateBookGenre(BookGenre bookGenre) throws SQLException {
        save("UPDATE tbl_book_genres SET bookId = ? AND genreId = ? WHERE bookId = ? and genreId = ?",
                new Object[] { bookGenre.getBook().getBookId(), bookGenre.getGenre().getGenreId(), bookGenre.getBook().getBookId(), bookGenre.getGenre().getGenreId() });
    }

    public void deleteBookGenre(BookGenre bookGenre) throws SQLException {
        save("DELETE FROM tbl_book_genres WHERE bookId = ? and genreId = ?",
                new Object[] { bookGenre.getBook().getBookId(), bookGenre.getGenre().getGenreId() });
    }
}
