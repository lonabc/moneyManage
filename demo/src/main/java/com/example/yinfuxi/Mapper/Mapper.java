package com.example.yinfuxi.Mapper;

import com.example.yinfuxi.Lover;
import com.example.yinfuxi.StaticMy.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("select * from bestlove where name=#{name}")
    public Lover selectMyName(String name);

    @Select("select id_test from bestlove where name=#{name} and password=#{password}")
    public int selectId(String name ,String password);


    public List<Lover> selectAll();
    @Delete("delete from bestlove where name=#{name}")
    public void deleteMy(String name);
    @Select("select * from bestlove where name=#{name}")
    public Lover selectIndividual(String name);

    @Select("select * from bestlove where age=#{age}")
    public ArrayList<Lover> selectSome(Integer age);
    @Insert("insert into bestlove(age,birthday,name,school) values (#{age},#{birthday},#{name},#{school})")
    public void insertForm(Lover lover);
    @Select("select name,password from bestlove where name=#{name} and password=#{password}")
    public Emp login(Emp e);
    @Select("select count(*) from bestlove ")
    public Integer sumItem();
    public void  update(Lover lover);

}
