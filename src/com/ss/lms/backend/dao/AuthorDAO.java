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

    public List<Author> readAuthors() throws SQLException, ClassNotFoundException {
        // Author + bookId
        return read("SELECT * FROM tbl_author",
                null);
    }

    public void addAuthor(String authorName) throws SQLException {
        save("INSERT INTO tbl_author(authorName) VALUES(?)",
                new Object[]{authorName});
    }

    public void updateAuthor(Author author) throws SQLException {
        save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
                new Object[] {
                        author.getName(),
                        author.getId()
                });
    }

    public void deleteAuthor(Author author) throws SQLException {
        save("DELETE FROM tbl_author WHERE authorId = ?",
                new Object[] {author.getId()});
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
}
