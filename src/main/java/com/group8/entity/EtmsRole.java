package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsRole {

  private long roleId;
  private String roleName;
  private String roleDescription;
  private String roleStatus;
  private String roleRemark;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;
  private List<EtmsPerm> permList;


}
