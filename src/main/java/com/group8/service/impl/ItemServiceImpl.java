package com.group8.service.impl;

import com.group8.dao.AbilityDao;
import com.group8.dao.ItemDao;
import com.group8.dao.OutlineDao;
import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.entity.EtmsAbilityModel;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsItemAm;
import com.group8.entity.EtmsOutline;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-14 16:25
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    ItemDao itemDao;
    @Autowired(required = false)
    AbilityDao abilityDao;
    @Autowired(required = false)
    OutlineDao outlineDao;

    /*
    添加培训项目 从DTO从取得3个对象
     */
    @Override
    public int addItem(EtmsItemAbilityOutline iao) {
        int i3  = 0;
        //添加培训项目
        EtmsItem etmsItem = iao.getEtmsItem();
        int i1 = itemDao.addOne(etmsItem);
        //添加大纲
        EtmsOutline etmsOutline = iao.getEtmsOutline();
        int i2 = outlineDao.addOne(etmsOutline);
        //循环添加能力模型
        List<EtmsItemAm> list = iao.getItemAmList();
        for (EtmsItemAm eia:list) {
            int i4 = abilityDao.addOne(eia);
            i3 = 1 ;
            //如果中间添加失败 则中断循环
            if(i4 < 0){
                i3 = 0;
                break;
            }
        }
        //如果其中一项不大于0 则添加失败
        if (i1 > 0 && i2 > 0 && i3 > 0){
            return 1;
        }else {
            return 0;
        }
    }
}
