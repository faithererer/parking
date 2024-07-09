package com.sdut.parking.system.mapper;

import com.sdut.parking.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    public List<Long> getMenuIdListByRoleId(Long roleId);

}
