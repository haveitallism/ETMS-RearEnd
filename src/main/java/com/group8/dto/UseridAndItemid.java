package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author acoffee
 * @create 2021-12-15 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseridAndItemid {
    //学员id
    int uid;
    //培训id
    int tid;
}
