package com.laoayu.parking.system.mapper;

import com.laoayu.parking.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 菜单映射器
 *
 * @author faithererer
 * @date 2024/07/08
 */
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> getMenuListByUserId(@Param("userId") Long userId,@Param("pid") Long pid);

}
