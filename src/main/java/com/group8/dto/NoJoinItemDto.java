package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoJoinItemDto {
    private String itemName;
    private String deptName;
    private int page;
    private int limit;
    private int userId;
}
