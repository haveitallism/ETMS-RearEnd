package com.group8.dao;

import com.group8.dto.AbilityModelSubject;
import com.group8.entity.EtmsAbilityModel;

import java.util.List;

public interface AbilityModelDao {
    List<EtmsAbilityModel> findAll(AbilityModelSubject abilityModelSubject);

    int addOne(AbilityModelSubject AbilityModelSubject);
}
