package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Publisher implements Serializable {

    @Serial
    private static final long serialVersionUID = -9165897535647429242L;
    private int publisherId;
    private String publisherName;
    private String publisherAddress;
    private String publisherPhone;

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return publisherId == publisher.publisherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId);
    }
}
