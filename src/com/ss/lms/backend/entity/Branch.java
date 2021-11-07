package com.ss.lms.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Branch implements Serializable {

    @Serial
    private static final long serialVersionUID = -2099922049222774223L;
    private int id;
    private String name;
    private String address;
    private List<Loan> loans = new ArrayList<>();
    private List<Stock> inventory = new ArrayList<>();

    public Branch(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Stock> getInventory() {
        return inventory;
    }

    public void setInventory(List<Stock> inventory) {
        this.inventory = inventory;
    }

    public void addStock(Stock stock) {
        this.inventory.add(stock);
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return id == branch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", loans=" + loans +
                ", inventory=" + inventory +
                '}';
    }
}
