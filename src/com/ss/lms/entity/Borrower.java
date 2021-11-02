package com.ss.lms.entity;

import java.util.Objects;

public class Borrower {

    private int cardNo;
    private String name;
    private String address;
    private String phone;

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return cardNo == borrower.cardNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo);
    }
}
