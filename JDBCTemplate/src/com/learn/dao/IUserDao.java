package com.learn.dao;

import com.learn.exception.DataAccessException;
import com.learn.mode.User;

import java.sql.Connection;

/**
 * Created by Shinelon on 2017/5/16.
 */
public interface IUserDao {
    public void insertUser(User user, Connection conn) throws DataAccessException;

    public int queryUser(User user, Connection conn);
}
