package com.ss.lms.entity;

import java.util.Objects;

public class LibraryBranch {

    private int branchId;
    private String branchName;
    private String branchAddress;

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
        LibraryBranch that = (LibraryBranch) o;
        return branchId == that.branchId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId);
    }
}
