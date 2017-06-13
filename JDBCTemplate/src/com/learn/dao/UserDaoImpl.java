package com.learn.dao;

import com.learn.exception.DataAccessException;
import com.learn.mode.User;
import com.learn.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by doubi.liu on 2017/5/18.
 * User对象数据操作方法
 */
public class UserDaoImpl implements IUserDao{

    @Override
    public void insertUser(User user, Connection conn) throws DataAccessException {
        JdbcTemplate jt=new JdbcTemplate(conn);
        String sql="insert into user(user_id,user_name,user_age) values(?,?,?);";
        jt.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUser_id());
                pstmt.setString(2,user.getUser_name());
                pstmt.setInt(3,user.getUser_age());
            }
        });
    }

    @Override
    public int queryUser(User user, Connection conn) {
        return 0;
    }

    public static void main(String[] args) {
        IUserDao iUserDao=new UserDaoImpl();
        Connection conn= ConnectionFactory.getConnection();
        JdbcTransaction jt=new JdbcTransaction();
        jt.beginTransaction(conn);
        User user=new User();
        user.setUser_id("aaa");
        user.setUser_name("kkkk");
        user.setUser_age(1);
        try {
            iUserDao.insertUser(user,conn);
            jt.commit(conn);
        } catch (DataAccessException e) {
            e.printStackTrace();
            jt.rollback(conn);
        }finally {
            DBUtils.close(conn);
        }
    }
}
