package com.example.yinfuxi.Mapper;

import com.example.yinfuxi.Lover;

import com.example.yinfuxi.StaticMy.User;
import com.example.yinfuxi.StaticMy.UserCost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Insert("insert into moneyeveryday (id,cost,dayTime,classify) values( #{id},#{cost},#{dayTime},#{classify})")
    public void insertMoney(User user);

    public void insertRegister(Lover lover);

    public List<User> selectAll();

    @Select("select classify,sum(cost) as cost from moneyeveryday group by classify")
    public List<UserCost> selectCost();

    @Select("select * from moneyeveryday where dayTime like concat('%',#{dayTime},'%')")
    public List<UserCost> selectTime(String dayTime);
}
