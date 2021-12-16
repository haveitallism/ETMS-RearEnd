package com.group8.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

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



}
