package com.feng.mapper;

import com.feng.bean.DepartmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {


    //获取所有部门
    public List<DepartmentInfo> getAllDepartment();

    //通过 id 获取 对应的部门
    public DepartmentInfo getDeptById(int dept_id);

    //通过id 删除部门
    public int deleteDeptById(@Param("id") String id);

    //获取部门的所有负责人，也就是部门表中的一列值。
    public List<DepartmentInfo> getDeptAllManager();

    //更新部门
    public void updateDept(DepartmentInfo departmentInfo);

    //添加部门
    public void addDept(DepartmentInfo departmentInfo);

    //获取部门对象 通过部门名称
    public List<DepartmentInfo> getDeptByName(String department_name);
}
