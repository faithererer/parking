package com.laoayu.parking.system.mapper;

import com.laoayu.parking.system.entity.UserPark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;


public interface UserParkMapper extends BaseMapper<UserPark> {
    int deleteByUserIdAndParkId(Long userId, Long parkId);
}
