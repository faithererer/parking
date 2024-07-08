package com.laoayu.parking.system.service.impl;

import com.laoayu.parking.system.entity.UserPark;
import com.laoayu.parking.system.mapper.UserParkMapper;
import com.laoayu.parking.system.service.IUserParkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserParkServiceImpl extends ServiceImpl<UserParkMapper, UserPark> implements IUserParkService {

}
