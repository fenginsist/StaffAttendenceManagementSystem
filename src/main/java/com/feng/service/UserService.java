package com.feng.service;


import com.feng.bean.DepartmentInfo;
import com.feng.bean.PageInfoBean;
import com.feng.bean.UserInfo;
import com.feng.dao.DepartmentDao;
import com.feng.dao.UserDao;
import com.feng.dao.UserLoginDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    /*
    * 用户登录
    * */
    public UserInfo userLogin(String name,String password){
        //输入name、password   返回对象；
        UserInfo user = null;
        try {
            user = UserLoginDao.userLogin(name,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // 获取所有用户
    public List<UserInfo> userSearch() {
        UserDao userDao = new UserDao();
        List<UserInfo> user = userDao.getUser();
        return user;
    }

    //根据id 获取用户
    public UserInfo getUserById(int id) {
        UserDao userDao = new UserDao();
        UserInfo user = userDao.getUserById(id);
        return user;
    }

    //根据id 获取部门
    public DepartmentInfo getDeptById(int dept_id) {
        DepartmentDao departmentDao = new DepartmentDao();
        DepartmentInfo departmentInfo = departmentDao.getDeptById(dept_id);
        return departmentInfo;
    }

    //根据user 的 id  获取 用户和部门
    public List<UserInfo> getUserAndDeptByUser_id(int id){
        UserDao userDao = new UserDao();
        List<UserInfo> userAndDept = userDao.getUserAndDeptByUser_id(id);
        return userAndDept;
    }


    //更新用户
    public void updateUser(UserInfo userInfo) {
        UserDao userDao = new UserDao();
        userDao.updateUser(userInfo);
    }

    //添加用户
    public void addUser(UserInfo user) {
        UserDao userDao = new UserDao();
        userDao.addUser(user);
    }


    //删除用户
    public void deleteUserByAccount(String account) {
        UserDao userDao = new UserDao();
        userDao.deleteUserByAccount(account);
    }

    /*
    * 根据姓名和部门  模糊查询
    * 需要判断
    * */
    public List<UserInfo> getUserByNameAndDept(String name,String dept){
        UserDao userDao = new UserDao();
        List<UserInfo> userInfos = new ArrayList<>();
        if ( (name == null ||"".equals(name)) && (dept!=null || dept.length()>0)){
            userInfos = userDao.getUserByDept(dept);

        }else if((name !=null || name.length()>0) && (dept==null || "".equals(dept))){
            userInfos = userDao.getUserByName(name);
        }else {
            userInfos = userDao.getUserByNameAndDept(name,dept);
        }
        System.out.println("数据：："+userInfos);

        return userInfos;
    }

    //复选框删除多用户
    public Map<String, Boolean> deleteManyUser(String account) {

        Map<String , Boolean> map = new HashMap<String , Boolean>();
        String[] accountArray = account.split(",");
        for (String a : accountArray){
            if (a != null && a.length()>0){

                UserDao userDao = new UserDao();
                int i = userDao.deleteUserByAccount(a);
                Boolean flag = null;
                if (i>0){
                    flag = true;
                }else{
                    flag = false;
                }
                map.put(a,flag);
            }
        }
        return map;
    }









    /**
     *   分页实现查询用户，并将信息封装进   PageInfoBean
     * @param pageNum
     * @param pageRecord
     * @param totalRecord
     * @return
     */
    public PageInfoBean getAllUserByPageInfo(Integer pageNum, int pageRecord, int totalRecord) {

        //有了当前页码，每页显示的条数 和 总共要显示的条数后，创建PageInfo 对象并计算其分页导航
        PageInfoBean pi = new PageInfoBean(pageNum,pageRecord,totalRecord);
        //获取 PageInfoBean 对象中的starIndex ,每一页的第一条记录的编号。
        int startIndex = pi.getStartIndex();

        // 从数据库中分页查询，并将查询到的 list 集合封装到 PageInfoBean 中
        UserDao userDao = new UserDao();
        List<PageInfoBean> pageInfoBean = userDao.getAllUserByPageInfo(startIndex,pageRecord);
        pi.setList(pageInfoBean);

        return pi;
    }
}
