package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsPerm {

  private long permId;
  private String permName;
  private String permStatus;
  private String permOperate;
  private String permLink;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
