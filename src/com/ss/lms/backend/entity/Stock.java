package com.ss.lms.backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable {

    private int branchId;
    private Book book;
    private int quantity;

    public Stock(int branchId, Book book, int quantity) {
        this.branchId = branchId;
        this.book = book;
        this.quantity = quantity;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return branchId == stock.branchId && book.equals(stock.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, book);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "branchId=" + branchId +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }

}
