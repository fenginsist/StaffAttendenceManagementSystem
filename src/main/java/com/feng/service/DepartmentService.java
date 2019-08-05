package com.feng.service;

import com.feng.bean.DepartmentInfo;
import com.feng.dao.DepartmentDao;

import java.util.List;


public class DepartmentService {

    //获取所有的部门
    public List<DepartmentInfo> getAllDepartment(){

        DepartmentDao departmentDao = new DepartmentDao();
        List<DepartmentInfo> departments = departmentDao.getAllDepartment();

        return departments;
    }

    //通过id 删除 部门
    public int deleteDeptByid(String id) {
        DepartmentDao departmentDao = new DepartmentDao();
        int i = departmentDao.deleteDeptById(id);
        return i;
    }

    //进入部门修改页面，并且通过id 获取相应的部门对象
    public DepartmentInfo getDeptById(String deptId) {
        DepartmentDao departmentDao = new DepartmentDao();
        DepartmentInfo departmentInfo = departmentDao.getDeptById(Integer.valueOf(deptId));
        return departmentInfo;
    }

    //获取部门的所有负责人，也就是部门表中的一列值。
    public List<DepartmentInfo> getDeptAllManager() {
        DepartmentDao departmentDao = new DepartmentDao();
        List<DepartmentInfo> departmentInfos = departmentDao.getDeptAllManager();
        return departmentInfos;
    }

    //更新部门
    public void updateDept(DepartmentInfo departmentInfo) {
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.updateDept(departmentInfo);
    }

    //添加部门
    public void addDept(DepartmentInfo departmentInfo) {
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.addDept(departmentInfo);
    }

    public List<DepartmentInfo> getDeptByName(String department_name) {
        DepartmentDao departmentDao = new DepartmentDao();
        List<DepartmentInfo> departmentInfos = departmentDao.getDeptByName(department_name);
        return departmentInfos;
    }
}
