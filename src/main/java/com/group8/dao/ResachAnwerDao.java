package com.group8.dao;

import com.group8.entity.EtmsResachAnwer;

import java.util.List;

/**
 * description: EtmsResachAnwerDao <br>
 * date: 2021/12/15 5:49 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */

public interface ResachAnwerDao {
    //查询所有答案
    List<EtmsResachAnwer> findALL();

    //新增答案
    Integer addEtmsResachAnwerOne(EtmsResachAnwer etmsResachAnwer);
}
