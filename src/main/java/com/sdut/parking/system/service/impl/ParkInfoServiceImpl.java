package com.sdut.parking.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.common.holder.UserHolder;
import com.sdut.parking.system.entity.ParkInfo;
import com.sdut.parking.system.entity.UserPark;
import com.sdut.parking.system.mapper.ParkInfoMapper;
import com.sdut.parking.system.mapper.UserParkMapper;
import com.sdut.parking.system.service.IParkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class ParkInfoServiceImpl extends ServiceImpl<ParkInfoMapper, ParkInfo> implements IParkInfoService {


    @Resource
    private ParkInfoMapper parkInfoMapper;
    @Resource
    private UserParkMapper userParkMapper;

    /**
     * 添加停车场信息
     * @param parkInfo
     */
    @Override
    public void addParkInfo(ParkInfo parkInfo) {
        //写入停车场表
        this.baseMapper.insert(parkInfo);
        // 写入用户-停车场关联
        UserPark userPark = new UserPark();
        userPark.setParkId(parkInfo.getParkId());
        userPark.setUserId(UserHolder.getUser().getUserId());
        userParkMapper.insert(userPark);
    }

    /**
     * 修改停车场信息
     * @param parkInfo
     */
    @Override
    public void updateParkInfo(ParkInfo parkInfo) {

        //根据parkId修改数据
        this.baseMapper.updateById(parkInfo);
    }

    /**
     * 根据parkId查停车场信息
     * @param parkId
     * @return
     */
    @Override
    public ParkInfo getParkInfoById(Long parkId) {

        ParkInfo parkInfo = this.baseMapper.selectById(parkId);
        return parkInfo;
    }

    /**
     * 删除停车场信息
     * @param parkId
     */
    @Override
    public void deleteParkInfoById(Long parkId) {

        //根据parkId删除数据
        this.baseMapper.deleteById(parkId);
        // 删除关联
        userParkMapper.deleteByUserIdAndParkId(UserHolder.getUser().getUserId(), parkId);
    }

    /**
     * 根据登录的用户查停车场List
     * @param userId
     * @return
     */
    @Override
    public List<ParkInfo> getParkInfoByUserId(Long userId) {
        //根据userId查停车场信息
        List<ParkInfo> parkInfoList = this.baseMapper.getParkInfoByUserId(userId);
        return parkInfoList;
    }

    @Override
    public ParkInfo selectParkInfoByParkId(Long parkId) {
        List<ParkInfo> parkInfoList = parkInfoMapper.selectParkInfoByParkId(parkId);
        if (parkInfoList.size() == 0 || parkInfoList.isEmpty()){
            throw new RuntimeException("未查到停车场");
        }
        return parkInfoList.get(0);
    }

    /**
     * 根据用户查总停车位和剩余停车位数量
     * @param userName
     * @return
     */
    @Override
    public Map<String, Object> getParkSpace(String userName) {
        return parkInfoMapper.getParkSpace(userName);
    }

    /**
     * 根据登录用户查停车场列表
     * @param page
     * @param parkName
     * @param parkAddress
     * @param userName
     * @return
     */
    @Override
    public Page<ParkInfo> getParkInfoList(Page<Object> page, String parkName, String parkAddress, String userName, Boolean likeSearch) {
        return parkInfoMapper.getParkInfoList(page, parkName, parkAddress, userName,likeSearch);
    }
}
