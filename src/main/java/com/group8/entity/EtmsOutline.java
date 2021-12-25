package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsOutline {

  private long outlineId;
  private long itemId;
  private String catalog;
  private String trainClassTitle;
  private String trainClassFile;
  private long trainHour;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private int trainStatus;
  private long trainSchedele;

}
