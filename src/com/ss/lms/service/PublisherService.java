package com.ss.lms.service;

import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherService {

    private final ConnectionUtil connUtil;

    public PublisherService(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    public List<Publisher> getAllPublishers() throws SQLException {
        Connection conn = null;
        List<Publisher> pubs = new ArrayList<>();
        try {
            conn = connUtil.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);
            pubs = pdao.readPublishers();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("something went wrong!");
            e.printStackTrace();
            if (conn != null) conn.rollback();
        } finally {
            if (conn != null) conn.close();
        }
        return pubs;
    }
}
