package com.feng.service;

import com.feng.bean.ReportInfo;
import com.feng.dao.ReportDao;

import java.util.List;

public class ReportService {
    ReportDao reportDao = new ReportDao();

    //获取所有日报
    public List<ReportInfo> getAllReport() {
        ReportDao reportDao = new ReportDao();
        List<ReportInfo> reportInfos = reportDao.getAllReport();
        return reportInfos;
    }

    //
    public ReportInfo getReportByAccount(String reportAccount) {
        ReportDao reportDao = new ReportDao();
        ReportInfo reportInfo = reportDao.getReportByAccount(reportAccount);
        return reportInfo;
    }

    public void updateReportByAccount(ReportInfo reportInfo) {
        reportDao.updateReportByAccount(reportInfo);
    }

    public void deleteReportByAccount(String account) {
        reportDao.deleteReportByAccount(account);
    }

    public void addReport(ReportInfo reportInfo) {
        reportDao.addReport(reportInfo);
    }
}
