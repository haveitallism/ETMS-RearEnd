package com.group8.service;

import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.entity.EtmsItem;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-14 16:03
 */
public interface ItemService {

    int addItem(EtmsItemAbilityOutline etmsItemAbilityOutline);

    List<EtmsItem> findItem(EtmsItem etmsItem);

}
