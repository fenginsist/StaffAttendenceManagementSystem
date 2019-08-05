package com.feng.dao;

import com.feng.bean.DepartmentInfo;
import com.feng.mapper.DepartmentMapper;
import com.feng.util.SessionOne;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartmentDao {


    //获取所有部门
    public List<DepartmentInfo> getAllDepartment(){
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        List<DepartmentInfo> department = mapper.getAllDepartment();
//        System.out.println("所有部门：："+department.toString());
        session.close();
        return department;
    }

    /**
     * 通过id 获取部门
     * @param dept_id
     * @return
     */
    public DepartmentInfo getDeptById(int dept_id) {

        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        DepartmentInfo dept = mapper.getDeptById(dept_id);
        session.close();
        return dept;
    }

    /**
     * 根据id 删除部门
     * @param id
     * @return
     */
    public int deleteDeptById(String id) {
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        int i = mapper.deleteDeptById(id);
        session.commit();
        session.close();
        return i;
    }

    //获取部门的所有负责人，也就是部门表中的一列值。
    public List<DepartmentInfo> getDeptAllManager() {
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        List<DepartmentInfo> departmentInfos = mapper.getDeptAllManager();
        session.close();
        return departmentInfos;
    }

    //更新部门
    public void updateDept(DepartmentInfo departmentInfo) {
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        mapper.updateDept(departmentInfo);
        session.commit();
        session.close();
    }

    //添加部门
    public void addDept(DepartmentInfo departmentInfo) {
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        mapper.addDept(departmentInfo);
        session.commit();
        session.close();
    }

    public List<DepartmentInfo> getDeptByName(String department_name) {
        SqlSession session = SessionOne.getSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        List<DepartmentInfo> departmentInfos =  mapper.getDeptByName(department_name);
        return departmentInfos;
    }
}
