package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemidAndCatalogName {
    //培训Id
    int id;
    //catalog目录名字
    String catalogName;
}
