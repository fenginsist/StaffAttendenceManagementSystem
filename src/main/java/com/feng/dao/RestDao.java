package com.feng.dao;

import com.feng.bean.RestInfo;
import com.feng.mapper.RestMapper;
import com.feng.mapper.UserMapper;
import com.feng.util.SessionOne;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RestDao {

    public List<RestInfo> getAllRest() {
        SqlSession session = SessionOne.getSession();
        RestMapper mapper = session.getMapper(RestMapper.class);
        List<RestInfo> restInfos = mapper.getAllRest();
        session.close();
        return restInfos;
    }

    public void deleteRestByAccount(String account) {
        SqlSession session = SessionOne.getSession();
        RestMapper mapper = session.getMapper(RestMapper.class);
        mapper.deleteRestByAccount(account);
        session.commit();
        session.close();
    }

    public RestInfo getRestByaccount(String account) {
        SqlSession session = SessionOne.getSession();
        RestMapper mapper = session.getMapper(RestMapper.class);
        RestInfo restInfo = mapper.getRestByaccount(account);
        session.close();
        return restInfo;
    }

    public void updateRest(RestInfo restInfo) {
        SqlSession session = SessionOne.getSession();
        RestMapper mapper = session.getMapper(RestMapper.class);
        mapper.updateRest(restInfo);
        session.commit();
        session.close();
    }

    public void addRest(RestInfo restInfo) {
        SqlSession session = SessionOne.getSession();
        RestMapper mapper = session.getMapper(RestMapper.class);
        int i = mapper.addRest(restInfo);
        System.out.println("插入了几条数据"+i);
        session.commit();
        session.close();
    }
}
