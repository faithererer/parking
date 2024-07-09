package com.sdut.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.system.entity.CarScan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 汽车扫描测绘仪
 *
 * @author faithererer
 * @date 2024/07/08
 */
public interface CarScanMapper extends BaseMapper<CarScan> {

    Page<CarScan> getCarScanList(Page<CarScan> page, String plateColor, String type, String direction, String userName);
}
