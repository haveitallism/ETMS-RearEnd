package com.group8.service.impl;

import com.group8.dao.PlanDao;
import com.group8.dto.PlanApprove;
import com.group8.dto.PlanDto;
import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsPlanBudget;
import com.group8.entity.EtmsUser;
import com.group8.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@Transactional
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
    public int addPlan(PlanDto planDto) {
        EtmsPlan etmsPlan = planDto.getEtmsPlan();
//        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//        sdf.applyPattern("yyyy-MM-dd'T'HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        //String data=sdf.format(date);
        etmsPlan.setPlanStarttime(date);
        int i1 = planDao.addPlan(etmsPlan);
        long planId = etmsPlan.getPlanId();

        List<EtmsApproveRecord> approveRecords = planDto.getApproveRecords();
        int i2 = planDao.addApproveRecord(approveRecords,planId);

        List<EtmsPlanBudget> budgets = planDto.getBudgets();
        int i3 = planDao.addBudget(budgets,planId);
        return i1+i2+i3;
    }

    @Override
    public List<EtmsPlan> findPlanSchedule(EtmsPlan etmsPlan) {
        return planDao.findPlanSchedule(etmsPlan);
    }

    @Override
    public List<EtmsPlan> findMyApprove(PlanApprove planApprove) {
        return planDao.findMyApprove(planApprove.getTitle(),planApprove.getUid());
    }

    @Override
    public List<EtmsPlan> findMyApproved(PlanApprove planApprove) {
        return planDao.findMyApproved(planApprove.getTitle(),planApprove.getUid());
    }

    @Override
    public EtmsPlan findPlanById(Integer pid) {
        EtmsPlan etmsPlan = planDao.findPlanById(pid);
        return etmsPlan;
    }

    @Override
    public List<EtmsUser> findUser(List<EtmsApproveRecord> etmsApproveRecordList) {
        return planDao.findUser(etmsApproveRecordList);
    }

    @Override
    public int updateApprovePass(Integer pid) {
        EtmsApproveRecord etmsApproveRecord = new EtmsApproveRecord();
        etmsApproveRecord.setPlanId(pid);
        EtmsApproveRecord approve = planDao.findApproveNow(pid) ;
       int i = planDao.updateApprovePass(etmsApproveRecord);
        int i1 = planDao.updateNextApprove(approve.getApproveNumber() + 1, pid);
        if(i1 == 1){
            return i1;
        }else{
            int i3 = planDao.updatePlanPass(pid);
            return i3;
        }
    }

    @Override
    public int updateApproveNopass(Integer pid) {
        int i = planDao.updateApproveNopass(pid);
        int i3 = planDao.updatePlanNopass(pid);
        return i3;
    }
}
