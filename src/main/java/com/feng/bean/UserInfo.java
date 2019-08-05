package com.feng.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class UserInfo {


    private String id;
    private String account;
    private String password;
    private String name;
    private String department_id;       //关联部门表
    private String department_name;
    private String sex;
    private Date birthday;
    private String mobile;
    private String email;
    private String user_type;
    private String mylevel;
    private Date create_time;
    private String state;

    private List<DepartmentInfo> departmentInfos;



}
