package com.group8.service;

import com.group8.dto.AbilityModelSubject;
import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.dto.TrainAndCatalogSchedule;
import com.group8.dto.UserAndItemid;
import com.group8.entity.*;

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

    int deleteOne(int itemId);

    List<EtmsAbilityModel> findAMById(int id);

    int updateAbilityModel(AbilityModelSubject abilityModelSubject);

    List<EtmsOutline> findItemInfo(int userId,int itemId,String catalog);

    String findClassVideo(long itemId, String catalog, String trainClassTitle);

    TrainAndCatalogSchedule findScheduleAndHour(int userId, int itemId);

    boolean recordVideoProgress(UserAndItemid userAndItemid);
}
