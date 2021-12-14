package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsExamination {

  private String examId;
  private String examType;
  private String examQuestion;
  private long examScore;
  private String examSubject;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;

}
