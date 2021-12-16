package com.group8.service.impl;

import com.group8.dao.ItemDao;
import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsClassFile;
import com.group8.entity.EtmsItem;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<EtmsCatalog> findCatalogByTid(int id) {
        List<EtmsCatalog> catalogs = itemDao.findCatalogByTid(id);
        return catalogs;
    }

    @Override
    public List<EtmsClassFile> findClassFileByCid(int id,String catalogName) {
        List<EtmsClassFile> classFiles = itemDao.findClassFileByCid(id,catalogName);
        return classFiles;
    }

    @Override
    public String findSchedule(int uid, int tid) {
        String schedule = itemDao.findSchedule(uid, tid);
        return schedule;
    }

    @Override
    public String findClassNum(int tid) {
        int allClass = itemDao.findClassNum(tid);
        String classNum = String.valueOf(allClass);
        return classNum;
    }
}
