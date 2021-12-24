package com.group8.service.impl;

import com.google.gson.Gson;
import com.group8.dao.OutlineDao;
import com.group8.dto.UploadFile;
import com.group8.entity.EtmsCatalog;
import com.group8.service.OutlineService;
import com.group8.utils.QiniuUtil;
import com.group8.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
//import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class OutlineServiceImpl implements OutlineService {

    @Autowired(required = false)
    OutlineDao outlineDao;

    @Override
    public List<EtmsCatalog> findByItemId(int id) {
        return outlineDao.findCatalog(id);
    }

    @Override
    public int uploadClassFile(int id, String filePath) {
        return outlineDao.uploadClassFile(id, filePath);
    }

    @Override
    public int deleteClassFile(int id) {
        return outlineDao.deleteClassFile(id);
    }

    /**
     * 上传视频
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @Override
    public boolean uploadFile(UploadFile uploadFile) throws IOException{
        String qiniuUrl = "r4f66awjt.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "acoffee";
        String key = QiniuUtil.getRandomCharacterAndNumber(10) + ".mp4";//生成随机文件名
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        boolean flag = false;
        String responseUrl = "";
        try{
            byte[] localFile = uploadFile.getFile().getBytes();
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;
            File file = FileUtils.multipartFileToFile(uploadFile.getFile());
            System.out.println(file.getName());
            //计算视频时长
            long trainHour = FileUtils.getVideoDuration(file);
//            long trainHour = FileUtils.getDuration(file);
            flag = outlineDao.uploadFile(responseUrl,uploadFile.getId(),trainHour);
            //删除我们用于计算时间生成的临时File
            FileUtils.delteTempFile(file);
            //file.delete();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
