package com.group8.service.impl;

import com.google.gson.Gson;
import com.group8.dao.UserDao;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsUser;
import com.group8.entity.EtmsUserAm;
import com.group8.service.UserService;
import com.group8.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import com.group8.entity.EtmsUser;
import com.group8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserDao userDao;

    @Override
    public List<EtmsUser> findAllUser() {
        List<EtmsUser> allUser = userDao.findAllUser();
        return allUser;
    }

    @Override
    public EtmsUser findUserById(int id) {
        EtmsUser user = userDao.findUserById(id);

        return user;
    }

    @Override
    public boolean updateUser(EtmsUser etmsUser) {
        boolean flag = userDao.updateUser(etmsUser);
        return flag;
    }

    @Override
    public boolean updatePassword(int id,String newPassword) {
        EtmsUser user = userDao.findUserById(id);
        //MD5加盐加密
        SimpleHash simpleHash = new SimpleHash("MD5", newPassword, user.getUserName() + "etms");
        String hex = simpleHash.toHex();
        boolean flag = userDao.updatePassword(id,hex);
        return flag;
    }

    //验证密码是否错误
    @Override
    public boolean validatePassword(int id, String oldPassword) {

        EtmsUser user = userDao.findUserById(id);
        String oldPass = userDao.validatePassword(id);
        System.out.println(oldPass);
        //MD5加盐加密
        SimpleHash simpleHash = new SimpleHash("MD5", oldPassword, user.getUserName() + "etms");
        //16进制后的密码
        String hex = simpleHash.toHex();


//        System.out.println(Arrays.toString(userPassword.getBytes()));
//        System.out.println(Arrays.toString(simpleHash.getBytes()));
        if(oldPass.equals(hex)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 2021.12.15日 新增：
     * 网站首页展示
     * 包含：用户课程 用户培训 用户个人能力展示
     * 但还有进度展示待完善
     * @param id
     * @return
     */
    @Override
    public EtmsUser findUserIndexById(int id) {
        EtmsUser etmsUser = userDao.findUserById(id);
        List<EtmsItem> itemById = userDao.findItemById(id);
        List<EtmsCourse> coursesByid = userDao.findCoursesByid(id);
        List<EtmsUserAm> abilityById = userDao.findAbilityById(id);
        etmsUser.setCourseList(coursesByid);
        etmsUser.setItemList(itemById);
        etmsUser.setUserAmList(abilityById);
        return etmsUser;
    }
    @Override
    public List<EtmsUser> findByDeptId(int deptId) {
        return userDao.findByDeptId(deptId);
    }

    @Override
    public EtmsUser login(EtmsUser etmsUser) {
        return userDao.findByUsernamAndPassword(etmsUser.getUserName(), etmsUser.getUserPassword());
    }


    @Override
    public String uploadPicture(UploadImg uploadImg) throws IOException {
        String qiniuUrl = "r4f66awjt.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "acoffee";
        String key = QiniuUtil.getRandomCharacterAndNumber(10) + ".jpg";//生成随机文件名
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        String responseUrl = "";
        try{
            byte[] localFile = uploadImg.getFile().getBytes();
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;
            boolean flag = userDao.uploadPicture(responseUrl,uploadImg.getUserId());

        }catch (QiniuException e){
            Response r = e.response;
        }
        return responseUrl;
    }



    @Override
    public List<EtmsUser> findAllStudent(EtmsUser etmsUser) {
        return userDao.findAllStudent(etmsUser);
    }

    @Override
    public int addStudent(EtmsUser etmsUser) {
        List<EtmsUser> list = userDao.checkUser(etmsUser);
        if(list.isEmpty()){
            int i = userDao.addStudent(etmsUser);
            return i;
        }else{
            return 0;
        }

    }

    @Override
    public int deleteStudent(int userId) {
        int i = userDao.deleteStudent(userId);
        return 0;
    }

    @Override
    public boolean updateStudent(EtmsUser etmsUser) {
        boolean b = userDao.updateStudent(etmsUser);
        return false;
    }

    @Override
    public int addCourse(int userId, int courseId) {

        Integer text = userDao.findUidCid(userId,courseId);
        if(text == null){
            return userDao.addCourse(userId,courseId);
        }else {
            return 0;
        }

    }


}
