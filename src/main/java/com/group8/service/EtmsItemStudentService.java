package com.group8.service;

import com.group8.entity.EtmsItem;

import java.util.List;

public interface EtmsItemStudentService {
    int findMyItemSum(int uid);

    List<EtmsItem> findItemByType(int uid);

    String findItemSchedele(int uid, int itemid);
}
