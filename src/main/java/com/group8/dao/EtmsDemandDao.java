package com.group8.dao;

import com.group8.entity.EtmsDemand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EtmsDemandDao {
    List<EtmsDemand> findAll();

    List<EtmsDemand> findByuid(int uid);

    int addOne(EtmsDemand etmsDemand);

    EtmsDemand findByid(Integer id);

    List<EtmsDemand> findByName(@Param("title") String title,@Param("uid") Integer uid);
}
