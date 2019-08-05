package com.feng.mapper;

import com.feng.bean.DepartmentInfo;
import com.feng.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    //用户登录
    public UserInfo userLogin(@Param("name") String name, @Param("password") String password);


    //获取所有用户
    public List<UserInfo> getUser();

    //通过id
    public UserInfo getUserById(int id);

    //通过account
    public UserInfo getUserByAccount(String account);

    //根据user 的 id  获取 用户和部门
    public List<UserInfo> getUserAndDeptByUser_id(int id);

    //更新用户
    public int updateUser(UserInfo userInfo);

    //添加用户
    public int addUser(UserInfo user);

    //删除用户
    public int deleteUserByAccount(@Param("account") String account);



    //模糊查询
        //根据姓名模糊查询
        public List<UserInfo> getUserByName(@Param("name") String name);
        //根据部门准确查询
        public List<UserInfo> getUserByDept(String dept);
        //根据姓名模糊和部门查询
        public List<UserInfo> getUserByNameAndDept(@Param("name") String name, @Param("dept") String dept);


}
