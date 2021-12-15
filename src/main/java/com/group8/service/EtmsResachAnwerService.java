package com.group8.service;

import com.group8.entity.EtmsResachAnwer;

import java.util.List;

/**
 * description: EtmsResachAnwerService <br>
 * date: 2021/12/16 5:27 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */

public interface EtmsResachAnwerService {
    //查询所有答案
    List<EtmsResachAnwer> findALL();

    //新增答案
    Integer addEtmsResachAnwerOne(EtmsResachAnwer etmsResachAnwer);
}
