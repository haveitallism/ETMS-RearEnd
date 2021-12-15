package com.group8.dao;

import com.group8.entity.EtmsItem;

public interface ItemDao {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);
}
