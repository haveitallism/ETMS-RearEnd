package com.group8.service;

import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItemStudent;

import java.util.List;

public interface StudentService {
    List<EtmsItemStudent> findAll(int id);

    List<EtmsItemStudent> findApplied(ScheduleQueryCondition scheduleQueryCondition);

    int deleteById(int stuId);

    int add(int itemId, int userId);

    int updateApplyStatus(String status, int stuId);

    EtmsItemStudent findByItemIdAndUserId(int itemId, int userId);
}
