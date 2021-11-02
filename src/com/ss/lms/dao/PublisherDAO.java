package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublisherDAO extends BaseDAO<Publisher> {

    public PublisherDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }

    public void addPublisher()
}
