package com.feng.service;

import com.feng.bean.WorkInfo;
import com.feng.dao.WorkDao;

import java.util.List;

public class WorkService {
    WorkDao workDao = new WorkDao();

    public List<WorkInfo> getAllWork() {
        List<WorkInfo> workInfos = workDao.getAllWork();
        return workInfos;
    }

    public void deleteWorkInsepectByAccount(String account) {
        workDao.deleteWorkInsepectByAccount(account);
    }
}
