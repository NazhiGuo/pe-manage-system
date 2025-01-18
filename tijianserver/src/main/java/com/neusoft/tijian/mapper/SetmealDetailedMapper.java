package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.SetmealDetailed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDetailedMapper {

    // 因为也是存在关联关系，我们不用注解实现，用映射文件实现
    public List<SetmealDetailed> listSetmealDetailedBySmId(Integer smId);

}
