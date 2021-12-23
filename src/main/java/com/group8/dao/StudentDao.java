package com.group8.dao;

import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItemStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<EtmsItemStudent> findAll(int id);

    List<EtmsItemStudent> finApplied(ScheduleQueryCondition scheduleQueryCondition);

    int deleteById(int stuId);

    int add(@Param("itemId") int itemId, @Param("userId") int userId);

    int updateApplyStatus(@Param("status") String status, @Param("stuId") int stuId);

    EtmsItemStudent findByItemIdAndUserId(int itemId, int userId);


}
