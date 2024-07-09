package com.laoayu.parking.system.service;

import com.laoayu.parking.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> loginByGithub(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    void addUser(User user);

    User getUserById(Long userId);

    void updateUser(User user);

    void deleteUserById(Long userId);

    /**
     * 根据userName查用户
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);

}
