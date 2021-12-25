package com.group8.dao;

import com.group8.dto.UserAndItemid;
import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsClassFile;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsOutline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);

    List<EtmsCatalog> findCatalogByTid(int id);

    List<EtmsClassFile> findClassFileByCid(@Param("id") int id, @Param("catalogName") String catalogName);

    String findSchedule(@Param("uid") int uid,@Param("tid") int tid);

    int findClassNum(int tid);

    int addOne(EtmsItem etmsItem) ;

    List<EtmsItem> findItem(EtmsItem etmsItem);

    List<EtmsItem> findAllItem(int uid);

    int findMyItemSum(int uid);

    int deleteOne(int itemId);

    List<EtmsOutline> findItemInfo(@Param("userId")int userId,@Param("itemId") int itemId,@Param("catalog") String catalog);

    String findClassVideo(@Param("itemId") int itemId,@Param("catalog") String catalog,@Param("trainClassTitle") String trainClassTitle);

    //通过itemId查询Outline中的所有信息
    List<EtmsOutline> findOutline(int itemId);

    //查询每个目录的时长
    List<Long> findCatalogTrainHour(@Param("itemId")int itemId, @Param("catalog")String catalog);

    //查询培训具体的目录的观看数量
    int findCatalogSchedele(@Param("catalog")String catalog,@Param("userId")int userId, @Param("itemId")int itemId);

    //查询整个培训已观看的课程数量
    int findTrainSchedele(@Param("userId")int userId, @Param("itemId")int itemId);

    //查询培训具体的目录的总数量
    int findTrainNumByCatalog(@Param("catalog")String catalog,  @Param("itemId")int itemId);

    boolean DeleteItemByUid(@Param("uid") int uid,@Param("tid") int tid);

    boolean recordVideoProgress(UserAndItemid userAndItemid);
}
