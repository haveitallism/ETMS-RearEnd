package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsPlan {

  private long planId;
  private long planUid;
  private String planTitle;
  private String planCollect;
  private String planFor;
  private long planMoney;
  private String planContent;
  private String planStatus;
  private String planPass;
  private Date planStarttime;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;

}
