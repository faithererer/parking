package com.sdut.parking.system.service.impl;

import com.sdut.parking.system.entity.Role;
import com.sdut.parking.system.entity.UserRole;
import com.sdut.parking.system.mapper.UserRoleMapper;
import com.sdut.parking.system.service.IRoleService;
import com.sdut.parking.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private IRoleService roleService;

    @Override
    public Role getRoleByUserId(Long userId) {
        System.out.println("权限请求:"+userId);
        UserRole userRole = query().eq("user_id", userId).one();
        Role role = roleService.getRoleById(userRole.getRoleId());
        return role;
    }

}
