package com.group8.service.impl;

import com.group8.dao.PermDao;
import com.group8.entity.EtmsPerm;
import com.group8.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermServiceImpl implements PermService {

    @Autowired(required = false)
    PermDao permDao;

    @Override
    public List<EtmsPerm> findAll() {
        return permDao.findAll();
    }

}
