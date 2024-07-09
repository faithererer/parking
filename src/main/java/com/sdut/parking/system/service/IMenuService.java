package com.sdut.parking.system.service;

import com.sdut.parking.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IMenuService extends IService<Menu> {
    /**
     * 获取所有菜单
     *
     * @return 列表<菜单>
     */
    List<Menu> getAllMenu();

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId 用户身份
     * @return 列表<菜单>
     */
    List<Menu> getMenuListByUserId(Long userId);
}
