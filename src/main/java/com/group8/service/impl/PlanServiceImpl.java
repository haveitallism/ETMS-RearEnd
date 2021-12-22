package com.group8.service.impl;

import com.group8.dao.PlanDao;
import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsUser;
import com.group8.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanDao planDao;
    @Override
    public List<EtmsPlan> findAllPlan() {
        return planDao.findAll();
    }

    @Override
    public List<EtmsPlan> findMyPlan(EtmsPlan etmsPlan) {
        return planDao.findByUid(etmsPlan);
    }

    @Override
    public List<EtmsPlan> findBySelect(EtmsPlan etmsPlan) {
        return planDao.findBySelect(etmsPlan);
    }

    @Override
    public int addPlan(EtmsPlan etmsPlan) {
        return planDao.addPlan(etmsPlan);
    }

    @Override
    public List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan) {
        return planDao.findPlanSchedule(etmsPlan);
    }

    @Override
    public List<EtmsPlan> findMyApprove(Integer uid) {
        return planDao.findMyApprove(uid);
    }

    @Override
    public List<EtmsPlan> findMyApproved(Integer uid) {
        return planDao.findMyApproved(uid);
    }

    @Override
    public EtmsPlan findPlanById(Integer pid) {
        return planDao.findPlanById(pid);
    }

    @Override
    public List<EtmsUser> findUser(List<EtmsApproveRecord> etmsApproveRecordList) {
        return planDao.findUser(etmsApproveRecordList);
    }
}
