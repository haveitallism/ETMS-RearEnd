package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsDemand extends EtmsResachTopic {

  private Integer demandTableId;
  private String demandTitle;
  private String demandInstructions;
  private Date startTime;
  private Date endTime;
  private Integer demandDept;
  private String peopleNumber;
  private String trainingMethods;
  private String demandReason;
  private String otherRequirements;
  private String status;
  private int userId;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private EtmsDemandResearch etmsDemandResearch;
  private List<EtmsResachTopic> etmsResachTopics;
}
