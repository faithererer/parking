package com.sdut.parking.system.mapper;

import com.sdut.parking.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {

    public List<String> getRoleNameByUserId(Long userId);

    User selectUserByUserName(String userName);
}
