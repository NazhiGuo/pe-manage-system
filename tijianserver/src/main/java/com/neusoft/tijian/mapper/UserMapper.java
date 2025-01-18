package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 页面的用户，手机号、密码 -- 查询是否有这个用户？

    /**
     * 登录
     * @param users
     * @return
     */
    @Select("select * from users where userId=#{userId} and password=#{password}")
    public Users getUsersByUserIdByPass(Users users);

    /**
     * 用户电话号码是否已经存在的验证
     * @param userId
     * @return
     */
    @Select("select * from users where userId=#{userId}")
    public Users getUsersById(String userId);

    /**
     * 注册
     * @param users
     * @return
     */
    @Insert("insert into users values(#{userId},#{password},#{realName},#{sex},#{identityCard},#{birthday},#{userType})")
    public int saveUsers(Users users);

}
