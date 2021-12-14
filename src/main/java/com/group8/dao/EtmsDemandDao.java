package com.group8.dao;

import com.group8.entity.EtmsDemand;

import java.util.List;

public interface EtmsDemandDao {
    List<EtmsDemand> findAll();

    List<EtmsDemand> findByuid(int uid);

    int addOne(EtmsDemand etmsDemand);
}
