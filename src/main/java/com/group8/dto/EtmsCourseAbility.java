package com.group8.dto;

import com.group8.entity.EtmsCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-21 12:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsCourseAbility {
    EtmsCourse etmsCourse;
    List<AbilityModelSubject> amSubjectLists;
}
