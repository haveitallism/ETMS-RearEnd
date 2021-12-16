package com.group8.service;

import com.group8.entity.EtmsUser;

import java.util.List;

/**
 * @author acoffee
 * @create 2021-12-14 15:56
 */
public interface UserService {
    List<EtmsUser> findAllUser();

    EtmsUser findUserById(int id);

    boolean updateUser( EtmsUser etmsUser);

    boolean updatePassword(int id,String newPassword);

    boolean validatePassword(int id,String oldPassword);

    EtmsUser findUserIndexById(int id);
}
