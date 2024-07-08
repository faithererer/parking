package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 用户停车
 *
 * @author faithererer
 * @date 2024/07/08
 */
@TableName("sys_user_park")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 停车场ID
     */
    private Long parkId;
}
