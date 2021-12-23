package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePerm {
    private long roleId;
    private String roleName;
    private String roleDescription;
    private String roleStatus;
    private String permList;
}
