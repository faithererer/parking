package com.sdut.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.system.entity.CarInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 汽车信息映射器
 *
 * @author faithererer
 * @date 2024/07/08
 */
public interface CarInfoMapper extends BaseMapper<CarInfo> {

    Page<CarInfo> getCarInfoList(Page<CarInfo> page, String belongName, String plateColor, String plateNum, String userName);

    CarInfo getByPlateNumber(String plateNum, Long parkId);
}
