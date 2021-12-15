package com.group8.dao;

import com.group8.entity.EtmsItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EtmsItemStudentDao {
    int findMyItemSum(int uid);

    List<EtmsItem> findItemByType(@Param("uid") int uid);

    String findItemSchedele(@Param("user_id") int uid,@Param("item_id") int itemid);
}
