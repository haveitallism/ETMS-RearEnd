package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsCourse {

  private int courseId;
  private String courseTitle;
  private String courseType;
  private String courseLevel;
  private String courseHour;
  private String courseStatus;
  private String courseDescription;
  private String courseCover;
  private String courseForm;
  private String courseRequired;
  private String courseAttachment;
  private String createdBy;
  private LocalDateTime createdTime;
  private String updatedBy;
  private Date updatedTime;


}
