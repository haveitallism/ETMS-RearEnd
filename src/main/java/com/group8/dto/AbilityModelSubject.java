package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 辅助类，用于用户、培训项目、课程查询、新增能力模型时使用同一个controller
 * subject代表能力模型的主体，可以是item、user、course，xml采用动态sql进行分类查询
 * id为用户id/项目id/课程id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityModelSubject {
    private String subject;
    private long subjectId;
    private long amId;
    private long score;
}
