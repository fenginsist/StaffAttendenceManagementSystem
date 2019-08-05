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
public class WorkInfo {

    private int record_id;
    private String account;
    private String name;
    private Date work_date;
    private Date start_time;
    private Date end_time;
    private Date work_time;
    private String work_cause;
    private String beikao;
    private String state;
}
