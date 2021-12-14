package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsUser {

  private String userId;
  private String userUsername;
  private String userPassword;
  private String userDept;
  private String userPosition;
  private String userTelephone;
  private String userEmail;
  private String userManager;
  private String userRole;
  private String userCompany;
  private String userHeadImg;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
