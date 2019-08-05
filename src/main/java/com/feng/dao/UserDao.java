package com.feng.dao;

import com.feng.bean.PageInfoBean;
import com.feng.bean.UserInfo;
import com.feng.mapper.UserMapper;
import com.feng.util.SessionOne;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /*
    * 获取所有用户
    * */
    public List<UserInfo>  getUser() {

        List<UserInfo> userList = new ArrayList<>();
            SqlSession session = SessionOne.getSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            userList = mapper.getUser();
//            System.out.println("所有用户：："+userList);
        return userList;
    }

    public UserInfo getUserById(int id) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserInfo user = mapper.getUserById(id);
        session.close();
        return user;
    }

    public UserInfo getUserByAccount(String account) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserInfo userInfo = mapper.getUserByAccount(account);
        return userInfo;
    }

    //根据user 的 id  获取 用户和部门
    public List<UserInfo> getUserAndDeptByUser_id(int id){
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserInfo> userAndDeptByUser_id = mapper.getUserAndDeptByUser_id(id);
        session.close();
        return userAndDeptByUser_id;
    }

    //更新用户
    public void updateUser(UserInfo userInfo) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.updateUser(userInfo);
        System.out.println("更新的数据：：："+i);
        session.commit();
        session.close();
    }

    //添加用户
    public void addUser(UserInfo user) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(user.toString());
        int i = mapper.addUser(user);
        session.commit();
        System.out.println("插入的行数：：："+i);
        session.close();

    }

    //删除用户
    public int deleteUserByAccount(String account) {

        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.deleteUserByAccount(account);
        session.commit();
        System.out.println("删除了几行数据：："+i);
        session.close();

        return i;
    }

    /*
     * 根据姓名和部门  模糊查询
     * 需要判断
     * */

    public List<UserInfo> getUserByDept(String dept) {
//        System.out.println("dept::"+dept);
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserInfo> userByDepts = mapper.getUserByDept(dept);
        System.out.println("1111"+userByDepts);
        return userByDepts;
    }

    public List<UserInfo> getUserByName(String name) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserInfo> userInfos = mapper.getUserByName(name);
        return userInfos;
    }

    public List<UserInfo> getUserByNameAndDept(String name, String dept) {
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserInfo> userInfos = mapper.getUserByNameAndDept(name,dept);
        return userInfos;
    }


    /**
     *
     * @param startIndex
     * @param pageRecord
     * @return
     */
    public List<PageInfoBean> getAllUserByPageInfo(int startIndex, int pageRecord) {
        return null;
    }
}
