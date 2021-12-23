package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author acoffee
 * @create 2021-12-23 0:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogSchedule {
    private String catalog;
    private Long catalogHour;
    private Integer catalogSchedule;
}
