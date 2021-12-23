package com.group8.dto;

import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsPlanBudget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPlan {
    private EtmsPlan etmsPlan;
    private List<EtmsPlanBudget> budgets;
    private List<EtmsApproveRecord> approveRecords;
}
