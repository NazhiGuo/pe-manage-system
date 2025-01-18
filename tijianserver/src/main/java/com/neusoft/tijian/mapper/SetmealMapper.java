package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    // 查询体检套餐列表（包含检查项信息）
    // 此方法的实现稍复杂，需要用到关联查询，所以不用注解来实现，用 xxxmapper.xml 映射文件实现
    public List<Setmeal> listSetmealByType(Integer type);

    //根据主键查询体检套餐
    @Select("select * from setmeal where smId=#{smId}")
    public Setmeal getSetmealById(Integer smId);
}