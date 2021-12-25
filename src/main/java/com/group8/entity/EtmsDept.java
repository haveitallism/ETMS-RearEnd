package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsDept {
    private Integer deptId;
    private String deptName;
    private List<EtmsDemand> etmsDemands;
}
