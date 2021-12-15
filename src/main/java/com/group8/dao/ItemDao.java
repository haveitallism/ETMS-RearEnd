package com.group8.dao;

import com.group8.entity.EtmsItem;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-14 16:34
 */

public interface ItemDao {
    int addOne(EtmsItem etmsItem) ;

    List findItem(EtmsItem etmsItem);
}
