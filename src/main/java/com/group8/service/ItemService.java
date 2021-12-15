package com.group8.service;

import com.group8.entity.EtmsItem;

public interface ItemService {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);
}
