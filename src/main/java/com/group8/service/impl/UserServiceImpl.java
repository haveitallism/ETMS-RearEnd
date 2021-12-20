package com.group8.service.impl;

import com.group8.dao.UserDao;
import com.group8.entity.*;
import com.group8.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
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
        //MD5加盐加密
        SimpleHash simpleHash = new SimpleHash("MD5", oldPassword, user.getUserName() + "etms");
        //16进制后的密码
        String hex = simpleHash.toHex();
        String userPassword = user.getUserPassword();
        System.out.println(Arrays.toString(userPassword.getBytes()));
        System.out.println(Arrays.toString(simpleHash.getBytes()));
        if(userPassword.equals(hex)){
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
}
