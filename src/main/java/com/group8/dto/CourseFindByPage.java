package com.group8.dto;

import com.group8.entity.EtmsCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QY
 * @create 2021-12-21 11:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFindByPage {
    int page;
    int limit;
    EtmsCourse etmsCourse;
}
