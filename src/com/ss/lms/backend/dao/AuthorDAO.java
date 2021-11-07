package com.ss.lms.backend.dao;

import com.ss.lms.backend.entity.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends BaseDAO <Author> {

    public AuthorDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Author> extractData(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author author = new Author(
                    rs.getInt("authorId"),
                    rs.getString("authorName"));
            authors.add(author);
        }
        return authors;
    }

    public List<Author> readAuthors() throws SQLException, ClassNotFoundException {
        // Author + bookId
        return read("SELECT * FROM tbl_author",
                null);
    }
}
