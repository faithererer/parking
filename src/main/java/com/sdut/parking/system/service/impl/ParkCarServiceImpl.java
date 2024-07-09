package com.sdut.parking.system.service.impl;

import com.sdut.parking.system.entity.ParkCar;
import com.sdut.parking.system.mapper.ParkCarMapper;
import com.sdut.parking.system.service.IParkCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ParkCarServiceImpl extends ServiceImpl<ParkCarMapper, ParkCar> implements IParkCarService {

}
