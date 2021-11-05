package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Branch implements Serializable {

    @Serial
    private static final long serialVersionUID = -8618072017683024590L;
    private int branchId;
    private String branchName;
    private String branchAddress;
    private List<BookCopies> inventory = new ArrayList<>();

    public Branch(int branchId, String branchName, String branchAddress) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    public Branch(int branchId) {
        this.branchId = branchId;
    }

    public void addInventory(BookCopies stock) {
        inventory.add(stock);
    }

    public List<BookCopies> getInventory() {
        return inventory;
    }

    public void setInventory(List<BookCopies> inventory) {
        this.inventory = inventory;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return branchId == branch.branchId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId);
    }
}
