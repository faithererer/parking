package com.sdut.parking.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.system.entity.CarScan;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ICarScanService extends IService<CarScan> {

    /**
     * 根据userId,和前端输入框内容查询扫描结果List
     * @param page
     * @param plateColor
     * @param type
     * @param direction
     * @param userName
     * @return
     */

    Page<CarScan> getCarScanList(Page<CarScan> page, String plateColor, String type, String direction, String userName);

    /**
     * 新增车辆进场信息
     * @param carScan
     */
    void addEntryCar(CarScan carScan);

    /**
     * 新增车辆出场信息
     * @param carScan
     */
    void exitCar(CarScan carScan);

    /**
     * 根据Id删除出入场记录
     * @param id
     */
    void deleteCarScanById(Long id);
}
