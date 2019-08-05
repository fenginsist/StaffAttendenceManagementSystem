package com.feng.dao;

import com.feng.bean.ReportInfo;
import com.feng.mapper.DepartmentMapper;
import com.feng.mapper.ReportMapper;
import com.feng.util.SessionOne;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ReportDao {

    //获取所有日报
    public List<ReportInfo> getAllReport() {

        SqlSession session = SessionOne.getSession();
        ReportMapper mapper = session.getMapper(ReportMapper.class);
        List<ReportInfo> reportInfos = mapper.getAllReport();
        session.close();
        return reportInfos;
    }

    public ReportInfo getReportByAccount(String reportAccount) {
        SqlSession session = SessionOne.getSession();
        ReportMapper mapper = session.getMapper(ReportMapper.class);
        ReportInfo reportInfo = mapper.getReportByAccount(reportAccount);
        session.close();
        return reportInfo;
    }

    public void updateReportByAccount(ReportInfo reportInfo) {
        SqlSession session = SessionOne.getSession();
        ReportMapper mapper = session.getMapper(ReportMapper.class);
        mapper.updateReportByAccount(reportInfo);
        session.commit();
        session.close();
    }

    public void deleteReportByAccount(String account) {
        SqlSession session = SessionOne.getSession();
        ReportMapper mapper = session.getMapper(ReportMapper.class);
        mapper.deleteReportByAccount(account);
        session.commit();
        session.close();
    }

    public void addReport(ReportInfo reportInfo) {
        SqlSession session = SessionOne.getSession();
        ReportMapper mapper = session.getMapper(ReportMapper.class);
        int i = mapper.addReport(reportInfo);
//        System.out.println("添加了几条数据：："+i);
        session.commit();
        session.close();
    }
}
