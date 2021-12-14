package com.group8.dao;

import com.group8.entity.EtmsItemStudent;

import java.util.List;

public interface StudentDao {
    List<EtmsItemStudent> findAll(int id);
}
