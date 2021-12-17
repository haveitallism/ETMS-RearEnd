package com.group8.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsAbilityModel {

  private long abilityId;
  private String abilityName;
  private long abilityFatherId;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private List<EtmsAbilityModel> childAbilityList;
  private String score;
  private Object subject;



}
