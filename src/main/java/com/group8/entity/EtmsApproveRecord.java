package com.group8.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsApproveRecord {
  private long recordId;
  private long processId;
  private long approveUserId;
  private String approveComments;
  private String approveResult;
  private long approveNumber;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private Date approveEndtime;
  private String approver;
  private long planId;
}
