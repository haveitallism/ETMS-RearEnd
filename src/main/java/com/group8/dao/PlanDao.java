package com.group8.dao;

import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsPlanBudget;
import com.group8.entity.EtmsUser;
import org.apache.ibatis.annotations.Param;

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

    int addApproveRecord(@Param("list") List<EtmsApproveRecord> list, @Param("planId") long planId);

    int addBudget(@Param("list")List<EtmsPlanBudget> budgets, @Param("planId")long planId);
}
