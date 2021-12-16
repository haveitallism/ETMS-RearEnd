package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto类，某个项目所有学员的进度查询条件的dto，同时包含分页参数以及项目id
 * username如果是空字符串代表姓名不限，不为空字符代表有姓名条件
 * position同username
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleQueryCondition {
    private String username;
    private String position;
    private int page;
    private int limit;
    private int itemId;
}
