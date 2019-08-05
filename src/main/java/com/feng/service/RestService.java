package com.feng.service;

import com.feng.bean.RestInfo;
import com.feng.dao.RestDao;

import java.util.List;

public class RestService {
    RestDao restDao = new RestDao();
    public List<RestInfo> getAllRest() {
        List<RestInfo> restInfos =  restDao.getAllRest();
        return restInfos;
    }

    public void deleteRestByAccount(String account) {
        restDao.deleteRestByAccount(account);
    }


    public RestInfo getRestByAccount(String account) {
        RestInfo restInfo = restDao.getRestByaccount(account);
        return restInfo;
    }

    public void updateRest(RestInfo restInfo) {
        restDao.updateRest(restInfo);
    }

    public void addRest(RestInfo restInfo) {
        restDao.addRest(restInfo);
    }
}
