package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接收培训详情页一的所有参数
 * @author acoffee
 * @create 2021-12-22 20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainAndCatalogSchedule {
    int itemSchedule;
    List<CatalogSchedule> catalogSchedules;
}
