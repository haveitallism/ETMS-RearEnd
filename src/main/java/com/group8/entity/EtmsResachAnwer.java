package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * description: EtmsResachAnwer <br>
 * date: 2021/12/15 4:24 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//答案表实体类
public class EtmsResachAnwer {
    private Integer answerId;        //答案id
    private String  answerContent;   //答案内容
    private String  creatorid;        //创建人
    private Date    createtime;       //创建人时间
    private String  updated;          //修改人
    private Date    updatetime;       //修改人时间
    List<EtmsResachTopic>etmsResachTopics;
}
