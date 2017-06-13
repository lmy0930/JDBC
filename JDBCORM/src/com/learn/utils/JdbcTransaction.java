package com.learn.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by doubi.liu on 2017/5/18.
 * 数据库事务处理模板
 */
public class JdbcTransaction {
    public void beginTransaction(Connection conn){
        try {
            conn.setAutoCommit(false);//设置手动连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit(Connection conn){
        try {
            conn.commit();//提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(Connection conn){
        try {
            conn.rollback();//事务回滚
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
