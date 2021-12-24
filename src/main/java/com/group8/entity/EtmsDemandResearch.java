package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsDemandResearch {
  private long researchId; //调研表id
  private Date startTime;  //开始时间
  private Date endTime;    //结束时间
  private long demandId;   //需求表的id
  private long userId;    //普通用户填写用户的id
  private String status;   //状态
  private String createdBy; //创建人
  private Date createdTime; //创建人的时间
  private String updatedBy; //更新人
  private Date updatedTime; //更新人时间
  private List<EtmsResachTopic> etmsResachTopicList;
}
