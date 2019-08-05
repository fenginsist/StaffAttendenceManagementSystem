package com.feng.dao;

import com.feng.bean.UserInfo;
import com.feng.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserLoginDao {

/*
* 登陆
* */
    public static UserInfo userLogin(String name,String password) throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserInfo userInfo = mapper.userLogin(name,password);

        System.out.println("login::"+userInfo.toString());
        session.close();
        return  userInfo;

    }

}
