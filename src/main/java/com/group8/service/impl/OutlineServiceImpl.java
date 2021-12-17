package com.group8.service.impl;

import com.group8.dao.OutlineDao;
import com.group8.entity.EtmsCatalog;
import com.group8.service.OutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutlineServiceImpl implements OutlineService {

    @Autowired(required = false)
    OutlineDao outlineDao;

    @Override
    public List<EtmsCatalog> findByItemId(int id) {
        return outlineDao.findCatalog(id);
    }

    @Override
    public int uploadClassFile(int id, String filePath) {
        return outlineDao.uploadClassFile(id, filePath);
    }
}
