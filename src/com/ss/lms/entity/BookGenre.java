package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class BookGenre implements Serializable {

    @Serial
    private static final long serialVersionUID = 7950913392753817875L;
    private Genre genre;
    private Book book;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return genre.equals(bookGenre.genre) && book.equals(bookGenre.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, book);
    }
}
