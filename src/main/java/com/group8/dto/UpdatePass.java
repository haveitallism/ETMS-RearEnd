package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接受密码的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePass {
    int id;
    String oldPassword;
    String pass;
}
