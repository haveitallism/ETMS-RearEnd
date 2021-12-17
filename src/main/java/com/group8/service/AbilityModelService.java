package com.group8.service;

import com.group8.dto.AbilityModelSubject;
import com.group8.entity.EtmsAbilityModel;

import java.util.List;

public interface AbilityModelService {
    List<EtmsAbilityModel> findAll(AbilityModelSubject abilityModelSubject);
}
