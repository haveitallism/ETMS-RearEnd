package com.group8.dao;

import com.group8.entity.EtmsDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptDao {
    List<EtmsDept> findAll();

    public EtmsDept selectEtmsDeptById(Integer deptId);

    List<EtmsDept> findAlls(@Param("etmsDept") EtmsDept etmsDept);
}
