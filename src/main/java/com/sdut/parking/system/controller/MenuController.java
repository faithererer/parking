package com.sdut.parking.system.controller;

import com.sdut.parking.common.vo.Result;
import com.sdut.parking.system.entity.Menu;
import com.sdut.parking.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 菜单控制器
 *
 * @author faithererer
 * @date 2024/07/08
 */
@Api(tags = "用户菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("查询所有菜单数据")
    @GetMapping
    public Result<?> getAllMenu() {

        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }

}
