package com.example.yinfuxi.Service.UserService;

import com.example.yinfuxi.Mapper.UserMapper;
import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.StaticMy.User;
import com.example.yinfuxi.StaticMy.UserCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyService implements MoneyServiceImp{

    @Autowired
    UserMapper userMapper;
    @Override
    public void insertMoney(User user) {
       userMapper.insertMoney(user);
    }

    @Override
    public List<User> selectAll() {
       return userMapper.selectAll();
    }

    @Override
    public Result selectCost(){
        List<UserCost> list =userMapper.selectCost();
        List<Object> classify=new ArrayList<>();
        List<Integer> cost=new ArrayList<>();
        for (UserCost userCost : list) {
           classify.add(userCost.getClassify());
           cost.add(userCost.getCost());
        }
       Result<List<Integer>> result=new Result<>();
        result.setData(classify);
        result.setTestData(cost);
        return result;
    }

    @Override
    public Result selectTime(String dayTime) {
        dayTime=dayTime.substring(0, 7);
        List<UserCost> list =userMapper.selectTime(dayTime);
        List<Object> classify=new ArrayList<>();
        List<Integer> cost=new ArrayList<>();
        for (UserCost userCost : list) {
            classify.add(userCost.getClassify());
            cost.add(userCost.getCost());
        }
        Result<List<Integer>> result=new Result<>();
        result.setData(classify);
        result.setTestData(cost);

        return result;
    }

}
