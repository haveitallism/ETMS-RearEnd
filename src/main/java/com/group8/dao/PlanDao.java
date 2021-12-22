package com.group8.dao;

import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsUser;

import java.util.List;

public interface PlanDao {
    List<EtmsPlan> findAll();

    List<EtmsPlan> findByUid(EtmsPlan etmsPlan);

    List<EtmsPlan> findBySelect(EtmsPlan etmsPlan);

    int addPlan(EtmsPlan etmsPlan);

    List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan);

    String findApprove();

    List<EtmsPlan> findMyApprove(int uid);

    List<EtmsPlan> findMyApproved(Integer uid);

    EtmsPlan findPlanById(Integer pid);

    List<EtmsUser> findUser(List<EtmsApproveRecord> list);
}
