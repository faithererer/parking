package com.laoayu.parking.system.service.impl;

import com.laoayu.parking.system.entity.ParkCar;
import com.laoayu.parking.system.mapper.ParkCarMapper;
import com.laoayu.parking.system.service.IParkCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ParkCarServiceImpl extends ServiceImpl<ParkCarMapper, ParkCar> implements IParkCarService {

}
