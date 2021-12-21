package com.group8.dao;

import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItemStudent;
import com.group8.entity.EtmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<EtmsItemStudent> findAll(int id);

    List<EtmsItemStudent> finApplied(ScheduleQueryCondition scheduleQueryCondition);

    int deleteByItemIdAndUserId(@Param("itemId") int itemId, @Param("userId") int userId);

    int add(@Param("itemId") int itemId, @Param("userId") int userId);


}
