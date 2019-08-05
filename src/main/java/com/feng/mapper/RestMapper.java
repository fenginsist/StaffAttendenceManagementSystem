package com.feng.mapper;

import com.feng.bean.RestInfo;

import java.util.List;

public interface RestMapper {

    public List<RestInfo> getAllRest();

    public void deleteRestByAccount(String account);

    public RestInfo getRestByaccount(String account);

    public void updateRest(RestInfo restInfo);

    public int addRest(RestInfo restInfo);
}
