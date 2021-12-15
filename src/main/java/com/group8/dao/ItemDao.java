package com.group8.dao;

import com.group8.entity.EtmsItem;

import java.util.List;

public interface ItemDao {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);

    int addOne(EtmsItem etmsItem) ;

    List findItem(EtmsItem etmsItem);
}
