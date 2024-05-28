package com.example.yinfuxi.Service.UserService;

import com.example.yinfuxi.Lover;
import com.example.yinfuxi.Mapper.Mapper;
import com.example.yinfuxi.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegister implements UserRegisterlmp{
    @Autowired
    UserMapper userMapper;
    @Override
    public void insertRegister(Lover lover) {
       userMapper.insertRegister(lover);
    }
}
