package com.group8.dto;

import com.group8.entity.EtmsDemand;
import com.group8.entity.EtmsDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: DemandPagedto <br>
 * date: 2021/12/24 2:35 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandPagedto {
    private Integer page;
    private Integer limit;
    //    private String demandTitle;
//    private String demandInstructions;
//    private Date endTime;
//    private Integer demandDept;
//    private int userId;
    private EtmsDemand etmsDemand;
    private EtmsDept etmsDept;
}
