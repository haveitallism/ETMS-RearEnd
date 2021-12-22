package com.group8.service;

import com.group8.entity.EtmsPlan;

import java.util.List;

public interface PlanService {

    List<EtmsPlan> findAllPlan();

    List<EtmsPlan> findMyPlan(EtmsPlan etmsPlan);

    List<EtmsPlan> findBySelect(EtmsPlan etmsPlan);

    int addPlan(EtmsPlan etmsPlan);

    List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan);

    List<EtmsPlan> findMyApprove(Integer uid);

    List<EtmsPlan> findMyApproved(Integer uid);

    EtmsPlan findPlanById(Integer pid);
}
