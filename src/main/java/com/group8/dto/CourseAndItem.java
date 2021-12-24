package com.group8.dto;

import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-24 14:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAndItem {
    List<EtmsCourse> etmsCourseList;
    List<EtmsItem> etmsItemList;
}
