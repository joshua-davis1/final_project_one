package com.ss.lms.dao;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO<Book> {

    public BookDAO(Connection conn) {
        super(conn);
    }

    public void addBook(Book book) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_book (title, pubId) VALUES (?, ?)",
                new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
    }

    public void updateBook(Book book) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book SET title = ? AND pubId = ? WHERE bookId =?",
                new Object[] {book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()});
    }

    public void deleteBook(Book book) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {book.getBookId()});
    }

    public List<Book> readBooks() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book join tbl_book_authors on tbl_book.bookId = tbl_book_authors.bookId join tbl_author on tbl_book_authors.authorId = tbl_author.authorId;", null);
    }

    @Override
    protected List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setPublisher(new Publisher(rs.getInt("pubId")));
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
            books.add(book);
        }
        return books;
    }
}
