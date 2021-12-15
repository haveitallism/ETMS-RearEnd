package com.group8.dto;

import com.group8.entity.EtmsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QY
 * @create 2021-12-15 15:10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemFindByPage {
    int page;
    int limit;
    EtmsItem etmsItem;

}
