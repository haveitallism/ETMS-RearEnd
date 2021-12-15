package com.group8.service;

import com.group8.entity.EtmsItem;
import com.group8.dto.EtmsItemAbilityOutline;
import java.util.List;

public interface ItemService {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);

    int addItem(EtmsItemAbilityOutline etmsItemAbilityOutline);

    List<EtmsItem> findItem(EtmsItem etmsItem);

}
