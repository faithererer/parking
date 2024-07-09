package com.sdut.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.system.entity.ParkInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface ParkInfoMapper extends BaseMapper<ParkInfo> {
    public List<ParkInfo> getParkInfoByUserId (Long userId);

    List<ParkInfo> selectParkInfoByParkId(Long parkId);

    @SuppressWarnings("MybatisXMapperMethodInspection")//解决@MapKey is required 报错问题
    Map<String, Object> getParkSpace(String userName);

    Page<ParkInfo> getParkInfoList(Page<Object> page, String parkName, String parkAddress, String userName,Boolean likeSearch);


}
