package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author acoffee
 * @create 2021-12-15 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsCatalog {
    private String catalogName;
    private List<EtmsClassFile> classFileList;
}
