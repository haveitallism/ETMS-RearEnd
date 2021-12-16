package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author acoffee
 * @create 2021-12-15 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemidAndCatalogName {
    //培训Id
    int id;
    //catalog目录名字
    String catalogName;
}
