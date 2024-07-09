package com.sdut.parking.system.mapper;

import com.sdut.parking.system.entity.UserPark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface UserParkMapper extends BaseMapper<UserPark> {
    int deleteByUserIdAndParkId(Long userId, Long parkId);
}
