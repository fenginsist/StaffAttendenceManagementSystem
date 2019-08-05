package com.feng.test;

import com.feng.bean.UserInfo;
import com.feng.mapper.UserMapper;
import com.feng.util.SessionOne;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PageHelperTest {


    @Test
    public void test01(){
        SqlSession session = SessionOne.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Page<Object> page = PageHelper.startPage(2, 2);//当前页，页的大小

        List<UserInfo> users = mapper.getUser();
//        PageInfoBean<UserInfo> info = new PageInfoBean<>(users);        //这样也可以

        //传入要连续显示多少页
        PageInfo<UserInfo> info = new PageInfo<>(users,5);        //这样也可以

        for (UserInfo a : users){
            System.out.println(a.toString());
        }

        /*System.out.println("PageHelper当前页码"+page.getPageNum());
        System.out.println("PageHelper总记录数"+page.getTotal());
        System.out.println("PageHelper每页的记录数"+page.getPageSize());
        System.out.println("PageHelper总页码"+page.getPages());*/
        // XXX

        System.out.println("PageInfoBean 当前页码"+info.getPageNum());
        System.out.println("PageInfoBean 总记录数"+info.getTotal());
        System.out.println("PageInfoBean 每页的记录数"+info.getPageSize());
        System.out.println("PageInfoBean 总页码"+info.getPages());
        System.out.println("PageInfoBean 是否是第一页"+info.isIsFirstPage());
        System.out.println("PageInfoBean 是否是最后一页"+info.isIsLastPage());
        // XXX

        int[] nums = info.getNavigatepageNums();
        for (int a : nums){
            System.out.println(a);
        }


    }


}
