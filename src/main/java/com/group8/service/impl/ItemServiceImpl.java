package com.group8.service.impl;

import com.group8.dao.AbilityModelDao;
import com.group8.dao.ItemDao;
import com.group8.dao.OutlineDao;
import com.group8.dao.StudentDao;
import com.group8.dto.AbilityModelSubject;
import com.group8.dto.CatalogSchedule;
import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.dto.TrainAndCatalogSchedule;
import com.group8.dto.*;
import com.group8.entity.*;
import com.group8.service.ItemService;
import com.group8.utils.TidyAbilityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    ItemDao itemDao;
    @Autowired(required = false)
    AbilityModelDao abilityModelDao;
    @Autowired(required = false)
    OutlineDao outlineDao;
    @Autowired(required = false)
    StudentDao studentDao;

    @Override
    public EtmsItem findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public int update(EtmsItem etmsItem) {
        return itemDao.update(etmsItem);
    }

    @Override
    public List<EtmsCatalog> findCatalogByTid(int id) {
        List<EtmsCatalog> catalogs = itemDao.findCatalogByTid(id);
        return catalogs;
    }

    @Override
    public List<EtmsClassFile> findClassFileByCid(int id,String catalogName) {
        List<EtmsClassFile> classFiles = itemDao.findClassFileByCid(id,catalogName);
        return classFiles;
    }

    @Override
    public String findSchedule(int uid, int tid) {
        String schedule = itemDao.findSchedule(uid, tid);
        return schedule;
    }

    @Override
    public String findClassNum(int tid) {
        int allClass = itemDao.findClassNum(tid);
        String classNum = String.valueOf(allClass);
        return classNum;
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
        LocalDateTime now = LocalDateTime.now();
        etmsItem.setCreatedTime(now);
        int i1 = itemDao.addOne(etmsItem);
        long itemId = etmsItem.getItemId();

        //添加大纲集合
        List<EtmsCatalog> catalogs = iao.getOutline().getCatalogs();
//        for (int i = 0; i < catalogs.size(); i++) {
//            etmsOutlines.get(i).setCatalog("目录" +1+ i);
//            etmsOutlines.get(i).setItemId(itemId);
//        }
        i2 = outlineDao.addOne(catalogs, itemId);

        //添加能力模型
        List<AbilityModelSubject> list = iao.getAmSubjectLists();
        for (AbilityModelSubject ability:list
             ) {
            ability.setSubjectId(itemId);
        }
        list.get(0).setSubject("item");
        i3 = abilityModelDao.addOne(list);
        //如果其中一项不大于0 则添加失败
        if (i1 > 0 && i2 > 0 && i3 > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    public List<EtmsItem> findItem (EtmsItem etmsItem){
        List<EtmsItem> list = itemDao.findItem(etmsItem);
        System.out.println("获取的集合:"+list);
        return list;
    }

    @Override
    public int findMyItemSum(int uid) {
        return itemDao.findMyItemSum(uid);
    }

    @Override
    public List<EtmsItem> findAllItem(int uid) {
        return itemDao.findAllItem(uid);
    }

    @Override
    public int deleteOne(int itemId) {
        return itemDao.deleteOne(itemId);
    }

    @Override
    public List<EtmsAbilityModel> findAMById(int id) {
        AbilityModelSubject modelSubject = new AbilityModelSubject();
        modelSubject.setSubjectId(id);
        modelSubject.setSubject("item");
        List<EtmsAbilityModel> abilityModelList = abilityModelDao.findAll(modelSubject);
        return TidyAbilityModel.tidy(abilityModelList);
    }

    @Override
    public int updateAbilityModel(AbilityModelSubject abilityModelSubject) {
        return abilityModelDao.updateAbilityModel(abilityModelSubject);
    }

    @Override
    public List<EtmsOutline> findItemInfo(int userId,int itemId, String catalog) {
        List<EtmsOutline> itemInfo = itemDao.findItemInfo(userId,itemId, catalog);
        return itemInfo;
    }

    @Override
    public String findClassVideo(long itemId, String catalog, String trainClassTitle) {
        String classVideo = itemDao.findClassVideo((int) itemId, catalog, trainClassTitle);
        return classVideo;
    }

    @Override
    public TrainAndCatalogSchedule findScheduleAndHour(int userId, int itemId) {
        TrainAndCatalogSchedule schedule = new TrainAndCatalogSchedule();
        List<CatalogSchedule> catalogSchedules = new ArrayList<>();

        List<EtmsOutline> outlines = itemDao.findOutline(itemId);
        //记录培训总时长
        long itemHour = 0L;
        //利用set去重得到目录名字
        HashSet<String> set = new HashSet();
        for(int i = 0;i < outlines.size();i++){
            set.add(outlines.get(i).getCatalog());
            itemHour += outlines.get(i).getTrainHour();
        }

        //存放去重后的目录
        List<String> list = new ArrayList();
        for(String catalog : set){
            list.add(catalog);
        }

        //得到目录名字以及对应课程时长的map集合
        HashMap map = new HashMap<String,List<Long>>();
        for (int i = 0;i < list.size();i++){
             List<Long> list1 = itemDao.findCatalogTrainHour(itemId,list.get(i));
             map.put(list.get(i),list1);
        }

        //记录目录及对应的总时长
        HashMap<String, Long> catalogAndHourMap = new HashMap<>();
        //计算每个catalog的时长
        for (int i = 0;i < list.size();i++) {
            List<Long> listcatalogHours = (List<Long>) map.get(list.get(i));
            long everyCatalogHours = 0;
            for (int j = 0;j < listcatalogHours.size();j++){
                //先判断是否为空，因为数据库里面有些train_hour字段是空的,不判断就会报空指针异常
                if(listcatalogHours.get(j) != null){
                    everyCatalogHours += listcatalogHours.get(j);
                }
                //我们这里采用先放到map中将目录去重，这里如果直接用对象接受 就会有很多相同目录名字的对象产生
                catalogAndHourMap.put(list.get(i),everyCatalogHours);
            }
            //计算完一个就归零
            everyCatalogHours = 0;
        }

        CatalogSchedule catalogSchedule = null;
        Iterator iter = catalogAndHourMap.entrySet().iterator();//获取key和value的set
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); //把hashmap转成Iterator再迭代到entry
            String key = (String) entry.getKey(); //从entry获取key
            Long val = (Long) entry.getValue(); //从entry获取value
            catalogSchedule = new CatalogSchedule(key,val,null);
            catalogSchedules.add(catalogSchedule);
        }

        //计算培训项目完成的进度
        int count = itemDao.findTrainSchedele(userId,itemId);
        int trainSchedule = ((count*100)/outlines.size());
        schedule.setItemSchedule(trainSchedule);

        studentDao.updateSchedule(itemId,userId,trainSchedule);

        //计算每个目录完成的进度
        for(int i = 0;i < list.size();i++){
            int TrainNumByCatalog = itemDao.findTrainNumByCatalog(list.get(i),itemId);
            int catalogSchedeleNum = itemDao.findCatalogSchedele(list.get(i), userId, itemId);
            int catalogSchedele = ((catalogSchedeleNum)*100/TrainNumByCatalog);
            for(int j = 0;j < catalogSchedules.size();j++){
                if(catalogSchedules.get(j).getCatalog() == list.get(i)){
                    catalogSchedules.get(j).setCatalogSchedule(catalogSchedele);
                }
            }
        }
        schedule.setCatalogSchedules(catalogSchedules);
        return schedule;
    }

    @Override
    public boolean recordVideoProgress(UserAndItemid userAndItemid) {
        boolean flag = itemDao.recordVideoProgress(userAndItemid);
        return flag;
    }
}

