package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Borrower implements Serializable {

    @Serial
    private static final long serialVersionUID = 7819118025427462553L;
    private int cardNo;
    private String name;
    private String address;
    private String phone;

    public Borrower(int cardNo, String name, String address, String phone) {
        this.cardNo = cardNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Borrower() {
    }

    public Borrower(Integer cardNo) {
        this.cardNo = cardNo;
    }

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
