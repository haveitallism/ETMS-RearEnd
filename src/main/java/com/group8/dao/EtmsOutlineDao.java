package com.group8.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface EtmsOutlineDao {

    List<String> findAllCatalog(@Param("item_id") int itemid);
}
