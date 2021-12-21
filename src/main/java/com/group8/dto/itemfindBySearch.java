package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class itemfindBySearch {
    private int page;
    private int limit;
    private int id;
    private String radio;
    private String itemTitle;
    private String itemStatus;
}
