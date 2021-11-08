package com.ss.lms.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class BookGenre implements Serializable {
    // relational table
    @Serial
    private static final long serialVersionUID = -6824785864815629368L;
    private int genreId;
    private int bookId;

    public BookGenre(int genreId, int bookId) {
        this.genreId = genreId;
        this.bookId = bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return genreId == bookGenre.genreId && bookId == bookGenre.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, bookId);
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                "genreId=" + genreId +
                ", bookId=" + bookId +
                '}';
    }
}
