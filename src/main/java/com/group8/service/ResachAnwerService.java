package com.group8.service;

import com.group8.dto.AnwersDto;
import com.group8.entity.EtmsResachAnwer;

import java.util.List;

/**
 * description: EtmsResachAnwerService <br>
 * date: 2021/12/16 5:27 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */

public interface ResachAnwerService {
    //查询所有答案
    List<EtmsResachAnwer> findALL();

    EtmsResachAnwer selectAnwerById(Integer answerId);

    //新增多个题目对应多个答案
    Integer addEtmsResachAnwerOne(AnwersDto anwersDto);
}
