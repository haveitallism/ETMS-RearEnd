package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsUser {

  private long userId;
  private String userName;
  private String userPassword;
  private long deptId;
  private EtmsDept dept;
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
  //该用户课程集合
  private List<EtmsCourse> courseList;
  //该培训课程集合
  private List<EtmsItem> itemList;
  //该能力模型集合
  private List<EtmsUserAm> userAmList;
  //用户token
  private String token;
  //用户角色集合
  private String[] roles;
}
