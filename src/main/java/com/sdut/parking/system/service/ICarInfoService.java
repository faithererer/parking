package com.sdut.parking.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.system.entity.CarInfo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ICarInfoService extends IService<CarInfo> {

    void addCarInfo(CarInfo carInfo);

    void updateCarInfo(CarInfo carInfo);

    CarInfo getCarInfoById(Long carId);

    void deleteCarInfoById(Long carId);

    Page<CarInfo> getCarInfoList(Page<CarInfo> page, String belongName, String plateColor, String plateNum, String userName);

    /**
     * 根据车牌号和停车场号判断是否为固定车 (该接口能查询到固定车所有信息所有用于完成订单时查询免费时间)
     * @param plateNum
     * @param parkId
     * @return
     */
    CarInfo getByPlateNumber(String plateNum, Long parkId);

    //CarInfo selectCarInfoByParkIdAndPlateNum(Long parkId, String plateNum);
}
