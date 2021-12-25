package com.group8.dto;

import com.group8.entity.EtmsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-14 20:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsItemAbilityOutline {
    EtmsItem etmsItem;
    Outline outline;
    List<AbilityModelSubject> amSubjectLists;
}
