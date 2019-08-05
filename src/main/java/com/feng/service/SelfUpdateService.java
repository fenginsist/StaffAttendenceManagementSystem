package com.feng.service;

import com.feng.bean.UserInfo;
import com.feng.dao.UserDao;

public class SelfUpdateService {


    //通过 account 获取用户
    public UserInfo getUserByAccount(String account) {

        UserDao userDao = new UserDao();
        UserInfo userInfo = userDao.getUserByAccount(account);
        return userInfo;
    }
}
