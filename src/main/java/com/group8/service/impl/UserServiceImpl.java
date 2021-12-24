package com.group8.service.impl;

import com.google.gson.Gson;
import com.group8.dao.AbilityModelDao;
import com.group8.dao.RoleDao;
import com.group8.dao.UserDao;
import com.group8.dto.AbilityModelSubject;
import com.group8.dto.UploadImg;
import com.group8.entity.*;
import com.group8.service.UserService;
import com.group8.utils.JWTUtils;
import com.group8.utils.QiniuUtil;
import com.group8.utils.TidyAbilityModel;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserDao userDao;
    @Autowired(required = false)
    AbilityModelDao abilityModelDao;
    @Autowired(required = false)
    RedisTemplate redisTemplate;
    @Autowired(required = false)
    RoleDao roleDao;

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
        //MD5加盐加密
        SimpleHash simpleHash = new SimpleHash("MD5", etmsUser.getUserPassword(), etmsUser.getUserName() + "etms");
        String hexPassword = simpleHash.toHex();
        EtmsUser user = userDao.findByUsernamAndPassword(etmsUser.getUserName(), hexPassword);
        if (user == null){
            return null;
        }else {
            //在这个RbacManager对象中封装token
            String jwt = JWTUtils.sign(user.getUserName(), hexPassword);
            user.setToken(jwt);

            //将token存入redis数据库中，并设置过期时间
            redisTemplate.opsForValue().set(user.getUserName(), user.getToken());
            redisTemplate.expire(user.getUserName(), 60, TimeUnit.MINUTES);

            return user;
        }
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
    public int updateStudent(EtmsUser etmsUser) {
        int b = userDao.updateStudent(etmsUser);
        return b;
    }

    @Override
    public EtmsUser getStudentById(int userId) {
        EtmsUser etmsUser = userDao.getStudentById(userId);
        return etmsUser;
    }

    @Override
    public List<EtmsAbilityModel> findAmById(int userId) {
        AbilityModelSubject modelSubject = new AbilityModelSubject();
        modelSubject.setSubjectId(userId);
        modelSubject.setSubject("user");
        List<EtmsAbilityModel> abilityModelList = abilityModelDao.findAll(modelSubject);
        return TidyAbilityModel.tidy(abilityModelList);
    }

    @Override
    public EtmsUser getInfo(String token) {
        EtmsUser etmsUser = userDao.findByUsernamAndPassword(JWTUtils.getUserName(token), JWTUtils.getPassword(token));
        EtmsRole etmsRole = roleDao.findById(Integer.parseInt(etmsUser.getUserRole()));
        String[] roles = {etmsRole.getRoleRemark()};
        etmsUser.setRoles(roles);
        return etmsUser;
    }

    /**
     * 根据token删除redis中的数据
     * @param token
     * @return
     */
    @Override
    public boolean logout(String token) {
        //通过token获取用户名
        String username = JWTUtils.getUserName(token);
        //根据用户名删除数据
        return redisTemplate.delete(username);
    }
}
