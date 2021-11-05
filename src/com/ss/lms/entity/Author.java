package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 2900274492016060965L;
    private int authorId;
    private String authorName;

    public Author(int authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }
}
