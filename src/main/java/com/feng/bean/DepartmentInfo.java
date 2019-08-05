package com.feng.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentInfo {

    private String department_id;
    private String department_name;
    private String manager;
    private int total_user;
    private String create_time;

}
