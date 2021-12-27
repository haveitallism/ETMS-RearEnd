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

    List<EtmsPlan> findMyApprove(@Param("title") String title,@Param("uid") Integer uid);

    List<EtmsPlan> findMyApproved(@Param("title") String title,@Param("uid") Integer uid);

    EtmsPlan findPlanById(Integer pid);

    List<EtmsUser> findUser(List<EtmsApproveRecord> list);

    int addApproveRecord(@Param("list") List<EtmsApproveRecord> list, @Param("planId") long planId);

    int addBudget(@Param("list")List<EtmsPlanBudget> budgets, @Param("planId")long planId);

    String findApproveUser();

    int updateApprovePass(EtmsApproveRecord etmsApproveRecord);
    EtmsApproveRecord findApproveNow(int pid);

    int updateNextApprove(@Param("anum") long approveNumber,@Param("pid")Integer pid);

    int updatePlanPass(Integer pid);

    int updateApproveNopass(Integer pid);

    int updatePlanNopass(Integer pid);
}
