package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto类，用于辅助分页查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormInLine {
    private int page;
    private int limit;
    private int id;
}
