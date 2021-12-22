package com.group8.service;

import com.group8.dto.UploadFile;
import com.group8.entity.EtmsCatalog;

import java.io.IOException;
import java.util.List;

public interface OutlineService {
    List<EtmsCatalog> findByItemId(int id);

    int uploadClassFile(int id, String filePath);

    boolean uploadFile(UploadFile uploadFile)throws IOException;
}
