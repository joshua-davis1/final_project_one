package com.ss.lms.dao;

import com.ss.lms.entity.BookAuthor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO extends BaseDAO<BookAuthor> {

    public BookAuthorDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<BookAuthor> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookAuthor> bookAuths = new ArrayList<>();
        while (rs.next()) {
            BookAuthor bookAuth = new BookAuthor();
            bookAuth.getBook().setBookId(rs.getInt("bookId"));
            bookAuth.getAuthor().setAuthorId(rs.getInt("authId"));
            bookAuths.add(bookAuth);
        }
        return bookAuths;
    }

    public List<BookAuthor> readBookAuthors() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_authors", null);
    }

    public void addBookAuthor(BookAuthor bookAuth) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?, ?)",
                new Object[] {bookAuth.getBook().getBookId(), bookAuth.getAuthor().getAuthorId()});
    }

    public void updateBookAuthor(BookAuthor bookAuth) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book_authors SET authId = ? WHERE bookId =?",
                new Object[] {bookAuth.getAuthor().getAuthorId(), bookAuth.getBook().getBookId()});
    }

    public void deleteBookAuthor(BookAuthor bookAuth) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_book_authors WHERE authorId = ? AND bookId =",
                new Object[] {bookAuth.getBook().getBookId(), bookAuth.getAuthor().getAuthorId()});
    }
}
