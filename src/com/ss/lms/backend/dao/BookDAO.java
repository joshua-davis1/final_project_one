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
