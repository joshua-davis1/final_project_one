package com.ss.lms.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 5743194798660652403L;
    private int id;
    private String title;
    private Publisher publisher;
    private List<Author> authors = new ArrayList<>();

    public Book(int id, String title, Publisher publisher, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
    }

    public Book(int id, String title, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
    }

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", authors=" + authors +
                '}';
    }
}
