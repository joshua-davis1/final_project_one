package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Book;
import com.ss.lms.backend.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO<Book> {

    public BookDAO(Connection conn) {
        super(conn);
    }

    public List<Book> readBooks() throws SQLException, ClassNotFoundException {
        // Book + Publisher
        return read("SELECT * FROM tbl_book as b join tbl_publisher as p on p.publisherId = b.pubId;", null);
    }

    public void addBook(String title, int pubId) throws SQLException {
        save("INSERT INTO tbl_book(title, pubId) VALUES(? , ?)",
                new Object[] {
                        title,
                        pubId
                });
    }

    public void updateBook(Book book) throws SQLException {
        save("UPDATE tbl_book SET title = ?, pubId = ? WHERE bookId = ?",
                new Object[] {
                        book.getTitle(),
                        book.getPublisher().getId(),
                        book.getId()
                });
    }

    public void deleteBook(Book book) throws SQLException {
        save("DELETE FROM tbl_book WHERE bookId = ?",
                new Object[] {book.getId()});
    }

    @Override
    protected List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Publisher publisher = new Publisher(
                    rs.getInt("publisherId"),
                    rs.getString("publisherName"),
                    rs.getString("publisherAddress"),
                    rs.getString("publisherPhone")
            );
            Book book = new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    publisher
                    );
            books.add(book);
        }
        return books;
    }


}
