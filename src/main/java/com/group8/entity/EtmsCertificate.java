package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsCertificate {

  private String cerId;
  private String cId;
  private String cerTitle;
  private String cerType;
  private String cerStatus;
  private String cerUploadTime;
  private String cerUploadPerson;
  private String createdBy;
  private Date createdTime;
  private String updatedBy;
  private Date updatedTime;


}
