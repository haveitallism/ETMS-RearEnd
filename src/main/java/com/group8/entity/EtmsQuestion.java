package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsQuestion {

  private String queId;
  private String queTitle;
  private String queContent;
  private String queAnswer;
  private String queType;
  private String queLevel;
  private long queScore;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
