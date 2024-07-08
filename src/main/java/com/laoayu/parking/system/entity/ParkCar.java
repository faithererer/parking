package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 停放车
 *
 * @author faithererer
 * @date 2024/07/08
 */
@TableName("park_car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 固定车ID
     */
    private Long carId;

    /**
     * 停车场ID
     */
    private Long parkId;

}
