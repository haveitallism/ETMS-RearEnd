package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsPlanBudget {
    private int budgetId;
    private int planId;
    private String budgetName;
    private int budgetNum;
}
