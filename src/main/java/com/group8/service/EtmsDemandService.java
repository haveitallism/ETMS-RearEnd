package com.group8.service;

import com.group8.entity.EtmsDemand;

import java.util.List;

public interface EtmsDemandService {
    List<EtmsDemand> findAllDemand();

    List<EtmsDemand> findMyDemand(int uid);

    int addDemand(EtmsDemand etmsDemand);

    EtmsDemand findDemandByid(Integer did);
}
