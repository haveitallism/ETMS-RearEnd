package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsItem {

  private long itemId;
  private String trainTitle;
  private String trainDescription;
  private String trainType;
  private String trainLevel;
  private String trainForm;
  private String trainStatus;
  private String trainPlan;
  private String trainBudget;
  private String trainPrinc;
  private String trainCover;
  private long trainHour;
  private long applyNum;
  private Date applyCuttime;
  private String applyCheck;
  private String applyForm;
  private Date trainBetime;
  private Date trainEndtime;
  private String trainAddress;
  private String trainTeacher;
  private String studyType;
  private String signIn;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
