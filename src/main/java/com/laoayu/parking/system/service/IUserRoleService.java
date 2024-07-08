package com.laoayu.parking.system.service;

import com.laoayu.parking.system.entity.Role;
import com.laoayu.parking.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IUserRoleService extends IService<UserRole> {
    Role getRoleByUserId(Long userId);
}
