package com.sdut.parking.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.common.vo.Result;
import com.sdut.parking.system.entity.CarInfo;
import com.sdut.parking.system.entity.User;
import com.sdut.parking.system.service.ICarInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 汽车信息控制器
 *
 * @author admin
 * @date 2024/07/08
 */
@Api(tags = {"固定车管理接口列表"})
@RestController
@RequestMapping("/carInfo")
public class CarInfoController {

    @Autowired
    private ICarInfoService carInfoService;

    @ApiOperation("查询根据条件查固定车接口，根据登录的用户名查对应管理的固定车List")
    @GetMapping("/getCarInfoList")
    public Result<Map<String, Object>> getCarInfoList(@RequestParam(value = "belongName",required = false) String belongName,
                                                       @RequestParam(value = "plateColor",required = false) String plateColor,
                                                       @RequestParam(value = "plateNum",required = false) String plateNum,
                                                       @RequestParam(value = "userName",required = false) String userName,
                                                       @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                       @RequestParam(value = "pageSize",required = false) Long pageSize){
//        LambdaQueryWrapper<CarInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.hasLength(belongName),CarInfo::getBelongName,belongName);
//        wrapper.like(StringUtils.hasLength(plateColor),CarInfo::getPlateColor,plateColor);
//        wrapper.like(StringUtils.hasLength(plateNum),CarInfo::getPlateNum,plateNum);
//
//        Page<CarInfo> page = new Page<>(pageNum,pageSize);
//        carInfoService.page(page,wrapper);
//
//        Map<String,Object> data = new HashMap<>();
//        data.put("total",page.getTotal());
//        data.put("rows",page.getRecords());

        Page<CarInfo> page = carInfoService.getCarInfoList(new Page<>(pageNum,pageSize),belongName,plateColor,plateNum,userName);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    //查询所有固定车信息
    @ApiOperation("查询所有固定车接口")
    @GetMapping("/allCar")
    public Result<List<CarInfo>> getAllCarInfo(){
        List<CarInfo> CarInfoList = carInfoService.list();
        return Result.success(CarInfoList,"查询成功");
    }

    //新增固定车信息
    @ApiOperation("新增固定车信息接口")
    @PostMapping
    public Result<?> addCarInfo(@RequestBody CarInfo carInfo) {

        carInfoService.addCarInfo(carInfo);
        return Result.success("新增固定车成功");
    }

    //固定车信息修改
    @ApiOperation("修改固定车信息接口")
    @PutMapping
    public Result<?> updateCarInfo(@RequestBody CarInfo carInfo) {

        carInfoService.updateCarInfo(carInfo);
        return Result.success("固定车信息修改成功");
    }

    //根据carId查询
    @ApiOperation("根据carId查询固定车接口")
    @GetMapping("/{carId}")
    public Result<CarInfo> getCarInfoById(@PathVariable("carId") Long carId) {
        CarInfo carInfo = carInfoService.getCarInfoById(carId);
        return Result.success(carInfo);
    }

    //删除固定车（逻辑删除）
    @ApiOperation("删除固定车信息接口")
    @DeleteMapping("/{carId}")
    public Result<User> deleteCarInfoById(@PathVariable("carId") Long carId) {
        carInfoService.deleteCarInfoById(carId);
        return Result.success("成功删除固定车");
    }
}
