package com.sdut.parking.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 停车令
 *
 * @author faithererer
 * @date 2024/07/08
 */
@TableName("park_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车辆ID
     */
    private Long carId;

    /**
     * 停车场ID
     */
    private Long parkId;

    /**
     * 是否固定车（0不是 1是）
     */
    private Integer type;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 车牌号
     */
    private String plateNum;

    /**
     * 图片地址
     */
    private String picture;

    /**
     * 进入停车场时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date entryTime;

    /**
     * 离开停车场时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date exitTime;

    /**
     * 停车时长
     */
    private Integer parkingDuration;

    /**
     * 停车收费金额
     */
    private BigDecimal parkFee;

    /**
     *  所属停车场名
     */
    @TableField(exist = false)
    private String parkName;

    /**
     * 收费规则
     */
    @TableField(exist = false)
    private BigDecimal parkRule;

    /**
     * 用户身份
     */
    Long userId;

}
