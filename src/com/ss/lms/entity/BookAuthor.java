package com.ss.lms.entity;

import java.util.List;

public class BookAuthor {
    private List<Book> books;
    private List<Author> authors;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


}
