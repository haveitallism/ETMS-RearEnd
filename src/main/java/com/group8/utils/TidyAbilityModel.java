package com.group8.utils;

import com.group8.entity.EtmsAbilityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 工具类
 */
public class TidyAbilityModel {
    /**
     * 传入一个未整理的能力模型集合，整理分级后返回一个能力模型集合
     * @param abilityModelList 未整理的集合
     * @return 整理完成的集合
     */
    public static List<EtmsAbilityModel> tidy (List<EtmsAbilityModel> abilityModelList) {
        //遍历查询每个能力模型是否是顶级能力
        for (EtmsAbilityModel abilityModel : abilityModelList) {
            // 如果能力的父级id为0，说明这个能力是顶级能力
            if (abilityModel.getAbilityFatherId() == 0) {
                List<EtmsAbilityModel> childList = new ArrayList<>();
                // 遍历每个能力模型，将父级id相同的放入对应的父级能力模型对象中
                for (EtmsAbilityModel model : abilityModelList) {
                    if (model.getAbilityFatherId() == abilityModel.getAbilityId()) {
                        childList.add(model);
                        abilityModel.setChildAbilityList(childList);
                    }
                }
            }
        }

        //根据条件删除集合中已被加入子级集合的元素，父级id不为0代表是子级id
        abilityModelList.removeIf(new Predicate<EtmsAbilityModel>() {
            @Override
            public boolean test(EtmsAbilityModel etmsAbilityModel) {
                if (etmsAbilityModel.getAbilityFatherId() != 0) {
                    return true;
                }
                return false;
            }
        });
        return abilityModelList;
    }
}
