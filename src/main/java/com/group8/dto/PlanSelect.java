package com.group8.dto;

import com.group8.entity.EtmsPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanSelect {
    EtmsPlan etmsPlan;
    Integer startPage;
}
