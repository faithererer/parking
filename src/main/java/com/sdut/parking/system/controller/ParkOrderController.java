package com.sdut.parking.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.parking.common.holder.UserHolder;
import com.sdut.parking.common.vo.Result;
import com.sdut.parking.system.entity.ParkOrder;
import com.sdut.parking.system.service.ICarInfoService;
import com.sdut.parking.system.service.IParkInfoService;
import com.sdut.parking.system.service.IParkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 停车订单控制器
 *
 * @author faithererer
 * @date 2024/07/08
 */
@Api(tags = {"车辆订单记录list"})
@RestController
@RequestMapping("/parkOrder")
public class ParkOrderController {

    @Autowired
    private IParkOrderService parkOrderService;

    @Resource
    private ICarInfoService carInfoService;
    @Resource
    IParkInfoService parkInfoService;

    @ApiOperation("查询根据条件查对应停车场订单，根据登录的用户名查对应List")
    @GetMapping("/getParkOrderList")
    public Result<Map<String, Object>> getParkOrderList(@RequestParam(value = "plateColor",required = false) String plateColor,
                                                        @RequestParam(value = "type",required = false) String type,
                                                        @RequestParam(value = "parkName",required = false) String parkName,
                                                        @RequestParam(value = "userName",required = false) String userName,
                                                        @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                        @RequestParam(value = "pageSize",required = false) Long pageSize){

        Page<ParkOrder> curPage = null;
        if(userName.equals("root")) {
            Page<ParkOrder> page = parkOrderService.getParkOrderList(new Page<>(pageNum,pageSize),plateColor,type,parkName,userName);
            curPage = page;
        }
        else {
            Page<ParkOrder> page = parkOrderService.getBaseMapper().selectPage(new Page<>(pageNum, pageSize),
                    new QueryWrapper<ParkOrder>()
                            .eq("user_id", UserHolder.getUser().getUserId())
            );
            curPage = page;
            curPage.getRecords().forEach((carScan -> {
                carScan.setParkName(parkInfoService.selectParkInfoByParkId(
                        carScan.getParkId()
                ).getParkName());
            }));
        }



        Map<String,Object> data = new HashMap<>();
        data.put("total",curPage.getTotal());
        data.put("rows",curPage.getRecords());

        return Result.success(data);
    }

    //删除订单
    @ApiOperation("删除订单")
    @DeleteMapping("/{id}")
    public Result<ParkOrder> deleteParkOrderById(@PathVariable("id") Long id) {
        parkOrderService.deleteParkOrderById(id);
        return Result.success("成功删除这条订单");
    }

    //查询对应管理的停车场的总收入数
    @ApiOperation("计算总收入")
    @GetMapping("/totalIncome")
    public Result<BigDecimal> getTotalIncome(@RequestParam(value = "userName", required = false) String userName) {

        // 计算订单的总收入
        BigDecimal totalIncome = parkOrderService.getTotalIncome(userName);

        return Result.success(totalIncome);
    }

    @ApiOperation("获取每日缴费金额")
    @GetMapping("/dailyPayments")
    public Result<List<Map<String, Object>>> getDailyPayments(
            @ApiParam(value = "开始日期", required = true) @RequestParam(value = "startDate") String startDate,
            @ApiParam(value = "结束日期", required = true) @RequestParam(value = "endDate") String endDate) {

        List<Map<String, Object>> dailyPayments = parkOrderService.getDailyPayments(startDate, endDate);

        return Result.success(dailyPayments);
    }

    /**
     * 按类型获取总收入
     *
     * @return 结果<列表 < 映射 < 字符串 ， 字符串>>>
     */
    @GetMapping("/type/income")
    public Result<List<Map<String,String>>> getTotalIncomeByType(){
        List<Map<String, String>> list = parkOrderService.getBaseMapper().selectList(new QueryWrapper<ParkOrder>()
                        .select("park_id", "SUM(park_fee) as totalIncome")
                        .groupBy("park_id"))
                .stream()
                .map(record -> Map.of(
                        "park_id", record.getParkId() != null ? record.getParkId().toString() : "N/A",
                        "totalIncome", record.getParkFee() != null ? record.getParkFee().toString() : "0"
                ))
                .collect(Collectors.toList());
        return Result.success(list);
    }

}