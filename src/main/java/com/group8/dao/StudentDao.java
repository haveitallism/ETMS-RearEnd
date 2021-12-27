package com.group8.dao;

import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsItemStudent;
import com.group8.entity.EtmsOutline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<EtmsItemStudent> findAll(int id);

    List<EtmsItemStudent> finApplied(ScheduleQueryCondition scheduleQueryCondition);

    int deleteById(int stuId);

    int add(@Param("itemId") long itemId, @Param("userId") long userId);

    int updateApplyStatus(@Param("status") String status, @Param("stuId") int stuId);

    EtmsItemStudent findByItemIdAndUserId(@Param("itemId") long itemId, @Param("userId") long userId);

    int updateSchedule(@Param("itemId") int itemId, @Param("userId")int userId, @Param("trainSchedule")int trainSchedule);

    List<EtmsItem> findAllByUserId(int userId);

    void addStuSchedele(@Param("userId") long userId, @Param("itemId") long itemId, @Param("outlineList") List<EtmsOutline> outlineList);
}
