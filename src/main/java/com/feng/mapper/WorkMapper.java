package com.feng.mapper;

import com.feng.bean.WorkInfo;

import java.util.List;

public interface WorkMapper {


    public List<WorkInfo> getAllWork();

    public void deleteWorkInsepectByAccount(String account);
}
