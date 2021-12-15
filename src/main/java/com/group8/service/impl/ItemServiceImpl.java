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


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    ItemDao itemDao;
    @Autowired(required = false)
    AbilityDao abilityDao;
    @Autowired(required = false)
    OutlineDao outlineDao;

    @Override
    public EtmsItem findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public int update(EtmsItem etmsItem) {
        return itemDao.update(etmsItem);
    }
    /*
    添加培训项目 从DTO从取得3个对象
     */
        @Override
        public int addItem (EtmsItemAbilityOutline iao){

            int i2 = 0;
            int i3 = 0;

            //添加培训项目
            EtmsItem etmsItem = iao.getEtmsItem();
            int i1 = itemDao.addOne(etmsItem);

            List<EtmsOutline> etmsOutlines = iao.getEtmsOutlines();
            for (EtmsOutline eoi : etmsOutlines) {
                i2 = outlineDao.addOne(eoi);
                //如果中间添加失败 则中断循环
                if (i2 < 0) {
                    break;
                }
            }

            //循环添加能力模型
            List<EtmsItemAm> list = iao.getItemAmLists();
            for (EtmsItemAm eia : list) {
                i3 = abilityDao.addOne(eia);
                //如果中间添加失败 则中断循环
                if (i3 < 0) {
                    break;
                }
            }
            //如果其中一项不大于0 则添加失败
            if (i1 > 0 && i2 > 0 && i3 > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        @Override
        public List<EtmsItem> findItem (EtmsItem etmsItem){
            System.out.println("123" + etmsItem);
            List<EtmsItem> list = itemDao.findItem(etmsItem);
            System.out.println(list);
            return list;
        }
    }

