package com.group8.service.impl;

import com.google.gson.Gson;
import com.group8.dao.AbilityModelDao;
import com.group8.dao.CourseDao;
import com.group8.dto.AbilityModelSubject;
import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.dto.UploadFile;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.CourseService;
import com.group8.utils.FileUtils;
import com.group8.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired(required = false)
    CourseDao courseDao;
    @Autowired(required = false)
    AbilityModelDao abilityModelDao;

    @Override
    public int findMyCourseSum(int uid) {
        return courseDao.findMyRequiredSum(uid);
    }

    @Override
    public List<EtmsCourse> findAllRequired(int uid, EtmsCourse etmsCourse) {
        return courseDao.findAllRequired(uid,etmsCourse);
    }

    /**
     * 查询所有的课程包括选修必修
     * @param etmsCourse
     * @return
     */
    @Override
    public List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse) {
        System.out.println("课程："+etmsCourse);
        return courseDao.findAllCourse1(etmsCourse);
    }

    /**
     * 添加课程
     * @param eca
     * @return
     */
    @Override
    public int addCourse(EtmsCourseAbility eca) {

        //添加课程
        EtmsCourse etmsCourse = eca.getEtmsCourse();
        LocalDateTime now = LocalDateTime.now();
        etmsCourse.setCreatedTime(now);
        int i1 = courseDao.addCourse(etmsCourse);
        //获取主键id
        int courseId = etmsCourse.getCourseId();

        //添加能力模型
        List<AbilityModelSubject> list = eca.getAmSubjectLists();
        for (AbilityModelSubject ability:list
        ) {
            ability.setSubjectId(courseId);
        }
        list.get(0).setSubject("course");
        int i2 = abilityModelDao.addOne(list);

        if(i1 >0 && i2 >0){
            return 1;
        }else {
            return 0;
        }

    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @Override
    public int deleteCourse(int courseId) {
        return courseDao.deleteCourse(courseId);
    }

    /**
     * 上传封面
     * @param uploadImg
     * @return
     */
    @Override
    public String uploadPicture(UploadImg uploadImg)  {
        String qiniuUrl = "r4f66awjt.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "acoffee";
        System.out.println("文件名字："+ uploadImg.getFormat());
        String key = QiniuUtil.getRandomCharacterAndNumber(10) + ".jpg";//生成随机文件名
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        String responseUrl = "";
        try{
            byte[] localFile = new byte[0];
            try {
                localFile = uploadImg.getFile().getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;

        }catch (QiniuException e){
            Response r = e.response;
        }
        return responseUrl;
    }

    @Override
    public List<EtmsCourse> findHotCourses() {
        return courseDao.findHotCourses();
    }

    @Override
    public List<EtmsCourse> findCompanyRecommend() {
        return courseDao.findCompanyRecommend();
    }

    /**
     * 给课程上传视频
     * @param uploadFile
     * @return
     */
    @Override
    public String uploadFile(UploadFile uploadFile) {
        String qiniuUrl = "r4f66awjt.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "acoffee";
        String key = QiniuUtil.getRandomCharacterAndNumber(10) + ".mp4";//生成随机文件名
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        String responseUrl = "";
        try{
            byte[] localFile = uploadFile.getFile().getBytes();
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;
            System.out.println(responseUrl);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseUrl;
    }

    @Override
    public boolean DeleteCourseByUid(int uid, int cid) {
        return courseDao.DeleteCourseByUid(uid,cid);
    }

    @Override
    public List<EtmsCourse> findAllCourse(int id) {
        return courseDao.findAllCourse(id);
    }

    @Override
    public int findMyElectiveSum(int uid) {
        return courseDao.findMyElectiveSum(uid);
    }

    @Override
    public EtmsCourse openCourse(int id) {
        EtmsCourse etmsCourse = courseDao.openCourse(id);
        return etmsCourse;
    }


    @Override
    public List<EtmsCourse> findAllElective(int uid, EtmsCourse etmsCourse) {
        return courseDao.findAllElective(uid,etmsCourse);
    }

    @Override
    public EtmsCourse findCourseById(int courseId) {
        EtmsCourse course = courseDao.findCourseById(courseId);
        return course;
    }

    @Override
    public List<EtmsUser> findStudentByCid(int id) {
        List<EtmsUser> list = courseDao.findStudentByCid(id);
        return list;
    }

}

