package com.example.yinfuxi.Service.UserService;


import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.StaticMy.User;
import com.example.yinfuxi.StaticMy.UserCost;

import java.util.List;
import java.util.Map;

public interface MoneyServiceImp {
    public void insertMoney(User user);
    public List<User> selectAll();
    public Result selectCost();
    public Result selectTime(String dayTime);
}
