package com.group8.service.impl;

import com.group8.service.EtmsItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtmsItemServiceImpl implements EtmsItemService {
    @Autowired(required = false)
    EtmsItemDao etmsItemDao;
}
