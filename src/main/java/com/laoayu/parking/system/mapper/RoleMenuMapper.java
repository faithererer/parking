package com.laoayu.parking.system.mapper;

import com.laoayu.parking.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    public List<Long> getMenuIdListByRoleId(Long roleId);

}
