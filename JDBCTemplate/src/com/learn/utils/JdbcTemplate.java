package com.learn.utils;

import com.learn.exception.DataAccessException;

import java.sql.*;

/**
 * Created by doubi.liu on 2017/5/18.
 * CRUD操作模板
 */
public class JdbcTemplate {
    private Connection conn;

    public JdbcTemplate(Connection conn) {
        this.conn = conn;
    }

    //查询功能
    public void query(String sql,RowCallBackHandler handler) throws DataAccessException {
        Statement stmt=null;
        ResultSet rs=null;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            if(handler!=null){
                handler.processRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage(),e);
        }finally {
            DBUtils.close(rs,stmt);
        }
    }
    //查询功能,带防sql注入
    public void query(String sql,PreparedStatementSetter prearesetter,RowCallBackHandler handler) throws DataAccessException {
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            pstmt=conn.prepareStatement(sql);
            if (prearesetter!=null){
                prearesetter.setValues(pstmt);
                rs=pstmt.executeQuery();
            }
            if (handler!=null){
                handler.processRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage(),e);
        }finally {
            DBUtils.close(rs,pstmt);
        }
    }

    //  C(Create) U(Update) D(Delete)
    public void update(String sql,PreparedStatementSetter setter) throws DataAccessException {
        PreparedStatement pstmt=null;
        try {
            pstmt=conn.prepareStatement(sql);
            if (setter!=null){
                setter.setValues(pstmt);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage(),e);
        }finally {
            DBUtils.close(null,pstmt);
        }

    }





}
