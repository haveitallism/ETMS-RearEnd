package com.group8.service;

import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsClassFile;
import com.group8.entity.EtmsItem;
import com.group8.dto.EtmsItemAbilityOutline;
import java.util.List;

import java.util.List;

public interface ItemService {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);

    List<EtmsCatalog> findCatalogByTid(int id);

    List<EtmsClassFile> findClassFileByCid(int id,String catalogName);

    String findSchedule(int uid, int tid);

    String findClassNum(int tid);

    int addItem(EtmsItemAbilityOutline etmsItemAbilityOutline);

    List<EtmsItem> findItem(EtmsItem etmsItem);

    int findMyItemSum(int uid);

    List<EtmsItem> findAllItem(int uid);

}