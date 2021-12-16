package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QY
 * @create 2021-12-15 20:34
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsUserAm {
    private int userId;
    private int amId;
    private int userAmScore;
}
