package com.feng.mapper;

import com.feng.bean.ReportInfo;

import java.util.List;

public interface ReportMapper {

    //获取日报
    public List<ReportInfo> getAllReport();

    public ReportInfo getReportByAccount(String reportAccount);

    public void updateReportByAccount(ReportInfo reportInfo);

    public void deleteReportByAccount(String account);

    public int addReport(ReportInfo reportInfo);
}
