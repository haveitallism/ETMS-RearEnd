package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsItemStudent {

  private String studentId;
  private long userId;
  private long itemId;
  private String applyStatus;
  private String trainSchedule;
  private long trainTestid;
  private String trainCode;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private List<EtmsItem> itemList;



}
