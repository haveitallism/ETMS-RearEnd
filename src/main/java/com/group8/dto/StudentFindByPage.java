package com.group8.dto;

import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFindByPage {
    int page;
    int limit;
    EtmsUser etmsUser;

}