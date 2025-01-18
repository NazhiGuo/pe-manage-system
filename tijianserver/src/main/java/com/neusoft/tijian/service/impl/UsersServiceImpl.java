package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.UserMapper;
import com.neusoft.tijian.po.Users;
import com.neusoft.tijian.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users getUsersByUserIdByPass(Users users) {
        return userMapper.getUsersByUserIdByPass(users);
    }

    @Override
    public Users getUsersById(String userId) {
        return userMapper.getUsersById(userId);
    }

    @Override
    public int saveUsers(Users users) {
        return userMapper.saveUsers(users);
    }
}
