package com.feng.dao;

import com.feng.bean.WorkInfo;
import com.feng.mapper.UserMapper;
import com.feng.mapper.WorkMapper;
import com.feng.util.SessionOne;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WorkDao {
    public List<WorkInfo> getAllWork() {

        SqlSession session = SessionOne.getSession();
        WorkMapper mapper = session.getMapper(WorkMapper.class);
        List<WorkInfo> workInfos = mapper.getAllWork();
        return workInfos;
    }

    public void deleteWorkInsepectByAccount(String account) {
        SqlSession session = SessionOne.getSession();
        WorkMapper mapper = session.getMapper(WorkMapper.class);
        mapper.deleteWorkInsepectByAccount(account);
        session.commit();
        session.close();
    }
}
