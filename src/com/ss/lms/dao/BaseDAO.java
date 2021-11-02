package com.ss.lms.dao;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO<T> {

    protected static Connection conn = null;

    public BaseDAO(Connection conn) {
        this.conn = conn;
    }

    protected void save(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if(vals!=null) {
            int ct = 1;
            for (Object o: vals) {
                pstmt.setObject(ct, o);
                ct++;
            }
        }
        pstmt.execute();
    }

    protected Integer saveWithPK(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if(vals!=null) {
            int ct = 1;
            for (Object o: vals) {
                pstmt.setObject(ct, o);
                ct++;
            }
        }
        pstmt.execute();
        ResultSet rs = pstmt.getGeneratedKeys();
        while(rs.next()) {
            return rs.getInt(1);
        }
        return null;
    }

    protected List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if(vals!=null) {
            int ct = 1;
            for (Object o: vals) {
                pstmt.setObject(ct, o);
                ct++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractData(rs);
    }

    abstract protected  List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
}
