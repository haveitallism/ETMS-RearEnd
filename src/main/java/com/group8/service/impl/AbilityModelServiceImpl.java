package com.group8.service.impl;

import com.group8.dao.AbilityModelDao;
import com.group8.dto.AbilityModelSubject;
import com.group8.entity.EtmsAbilityModel;
import com.group8.service.AbilityModelService;
import com.group8.utils.TidyAbilityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbilityModelServiceImpl implements AbilityModelService {

    @Autowired(required = false)
    AbilityModelDao abilityModelDao;

    @Override
    public List<EtmsAbilityModel> findAll(AbilityModelSubject abilityModelSubject) {
        List<EtmsAbilityModel> abilityModelList = abilityModelDao.findAll(abilityModelSubject);
        return TidyAbilityModel.tidy(abilityModelList);
    }
}
