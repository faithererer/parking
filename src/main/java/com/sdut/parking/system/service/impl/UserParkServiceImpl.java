package com.sdut.parking.system.service.impl;

import com.sdut.parking.system.entity.UserPark;
import com.sdut.parking.system.mapper.UserParkMapper;
import com.sdut.parking.system.service.IUserParkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserParkServiceImpl extends ServiceImpl<UserParkMapper, UserPark> implements IUserParkService {

}
