package com.laoayu.parking.system.service;

import com.laoayu.parking.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Long roleId);

    void updateRole(Role role);

    void deleteRoleById(Long roleId);
}
