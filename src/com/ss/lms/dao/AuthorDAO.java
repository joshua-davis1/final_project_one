package com.ss.lms.dao;

import com.ss.lms.entity.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends BaseDAO<Author> {

    public AuthorDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author author = new Author();
            author.setAuthorId(rs.getInt("id"));
            author.setAuthorName(rs.getString("AuthorName"));
            authors.add(author);
        }
        return authors;
    }

    public List<Author> readAuthors() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_author", null);
    }

    public void addAuthor(Author auth) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_author (name) VALUES (?)",
                new Object[] {auth.getAuthorName()});
    }

    public void updateAuthor(Author auth) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_author SET name = ? WHERE authId = ?",
                new Object[] {auth.getAuthorName(), auth.getAuthorId()});
    }

    public void deleteAuthor(Author auth) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_author WHERE id = ?",
                new Object[] {auth.getAuthorId()});
    }

}
