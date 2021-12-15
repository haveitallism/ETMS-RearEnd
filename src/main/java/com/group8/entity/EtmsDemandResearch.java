package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsDemandResearch {

  private long researchId;
  private Date startTime;
  private Date endTime;
  private long demandId;
  private String problemOne;
  private String problemTwo;
  private String problemThree;
  private String problemFour;
  private long userId;
  private String status;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
