package com.feng.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class RestInfo {

    private int rest_id;
    private String account;
    private String name;

    private Date rest_start_date;
    private Date start_time;
    private Date rest_end_date;
    private Date end_time;
    private Date rest_time;

    private String rest_cause;
    private String beikao;
    private String state;

}
