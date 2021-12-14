package com.group8.service.impl;

import com.group8.dao.ItemDao;
import com.group8.entity.EtmsItem;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    ItemDao itemDao;

    @Override
    public EtmsItem findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public int update(EtmsItem etmsItem) {
        return itemDao.update(etmsItem);
    }
}
