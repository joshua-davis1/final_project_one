package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.BookAuthor;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO extends BaseDAO<BookAuthor> {
    public BookAuthorDAO(Connection conn) {
        super(conn);
    }

    public List<BookAuthor> readBookAuthor() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_authors", null);
    }

    public void addBookAuthor(BookAuthor bookAuthor) throws SQLException {
        save("INSERT INTO tbl_book_authors(bookId, authorId) VALUES(?,?)",
                new Object[]{
                        bookAuthor.getBookId(),
                        bookAuthor.getAuthorId()
                });
    }

    public void deleteBookAuthor(BookAuthor bookAuthor) throws SQLException {
        save("DELETE FROM tbl_book_authors WHERE bookId = ? AND authorId = ?",
                new Object[] {
                        bookAuthor.getBookId(),
                        bookAuthor.getAuthorId()
                });
    }

    @Override
    protected List<BookAuthor> extractData(ResultSet rs) throws SQLException {
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        while (rs.next()) {
            BookAuthor bookAuthor = new BookAuthor(
                    rs.getInt("bookId"),
                    rs.getInt("authorId")
            );
            bookAuthorList.add(bookAuthor);
        }
        return bookAuthorList;
    }
}
