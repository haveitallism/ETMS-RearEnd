package com.group8.service;

import com.group8.dto.PlanApprove;
import com.group8.dto.PlanDto;
import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsUser;

import java.util.List;

public interface PlanService {

    List<EtmsPlan> findAllPlan();

    List<EtmsPlan> findMyPlan(EtmsPlan etmsPlan);

    List<EtmsPlan> findBySelect(EtmsPlan etmsPlan);

    int addPlan(PlanDto planDto);

    List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan);

    List<EtmsPlan> findMyApprove(PlanApprove planApprove);

    List<EtmsPlan> findMyApproved(PlanApprove planApprove);

    EtmsPlan findPlanById(Integer pid);

    List<EtmsUser> findUser(List<EtmsApproveRecord> etmsApproveRecordList);

    int updateApprovePass(Integer pid);

    int updateApproveNopass(Integer pid);
}
