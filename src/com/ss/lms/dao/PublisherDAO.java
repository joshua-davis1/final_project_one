package com.ss.lms.dao;

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

    public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_publisher", null);
    }

    @Override
    protected List<Publisher> extractData(ResultSet rs) throws SQLException {
        List<Publisher> pubs = new ArrayList<>();
        while (rs.next()) {
            pubs.add(new Publisher(rs.getInt("publisherId"), rs.getString("publisherName"),rs.getString("publisherAddress"),rs.getString("publisherPhone")));
        }
        return pubs;
    }
}
