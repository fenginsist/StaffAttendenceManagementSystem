package com.feng.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportInfo {

    private int report_id;
    private String name;
    private String account;
    private Date report_date;
    private String work_process;
    private String work_content;
    private String tomorrow_plan;
    private String problem;
    private String other;


}
