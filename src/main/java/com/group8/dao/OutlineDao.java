package com.group8.dao;

import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsOutline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutlineDao {
    List<EtmsCatalog> findCatalog(int id);

    int uploadClassFile(@Param("id") int id, @Param("filePath") String filePath);

    int addOne(List<EtmsOutline> outlines) ;

    boolean uploadFile(@Param("filePath") String filePath,@Param("id") int id,@Param("trainHour") long trainHour);

    int deleteClassFile(int id);
}
