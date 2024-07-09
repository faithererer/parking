package com.sdut.parking.system.service;

import com.sdut.parking.system.entity.Role;
import com.sdut.parking.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IUserRoleService extends IService<UserRole> {
    Role getRoleByUserId(Long userId);
}
