package com.group8.service;

import com.group8.dto.AddPlan;
import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsUser;

import java.util.List;

public interface PlanService {

    List<EtmsPlan> findAllPlan();

    List<EtmsPlan> findMyPlan(EtmsPlan etmsPlan);

    List<EtmsPlan> findBySelect(EtmsPlan etmsPlan);

    int addPlan(AddPlan addPlan);

    List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan);

    List<EtmsPlan> findMyApprove(Integer uid);

    List<EtmsPlan> findMyApproved(Integer uid);

    EtmsPlan findPlanById(Integer pid);

    List<EtmsUser> findUser(List<EtmsApproveRecord> etmsApproveRecordList);
}
