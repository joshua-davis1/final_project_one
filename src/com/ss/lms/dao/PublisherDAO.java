package com.ss.lms.dao;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO extends BaseDAO<Publisher> {

    public PublisherDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Publisher> extractData(ResultSet rs) throws SQLException {
        List<Publisher> pubs = new ArrayList<>();
        while (rs.next()) {
            Publisher pub = new Publisher();
            pub.setPublisherId(rs.getInt("id"));
            pub.setPublisherName(rs.getString("publisherName"));
            pub.setPublisherAddress(rs.getString("publisherAddress"));
            pub.setPublisherPhone(rs.getString("publisherPhone"));
            pubs.add(pub);
        }
        return pubs;
    }

    public void addPublisher(Publisher pub) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?)",
                new Object[] {pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone()});
    }

    public void updatePublisher(Publisher pub) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_publisher SET publisherName = ? AND publisherAddress = ? AND publisherPhone = ? WHERE publisherId =?",
                new Object[] {pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone(), pub.getPublisherId()});
    }

    public void deletePublisher(Publisher pub) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_publisher WHERE publisherId =?",
                new Object[] {pub.getPublisherId()});
    }

    public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_publisher", null);
    }


}
