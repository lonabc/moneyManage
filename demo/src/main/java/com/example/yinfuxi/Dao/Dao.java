package com.example.yinfuxi.Dao;


import com.example.yinfuxi.Lover;
import com.example.yinfuxi.Mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Dao  implements DaoParent{
    @Autowired
    Mapper mapper;
    public Lover daoMy(String name)
    {
         Lover lover=mapper.selectMyName(name);
         return lover;
    }

    @Override
    public List<Lover> daoAll() {
        List<Lover> lovers=mapper.selectAll();
        return lovers;
    }

    @Override
    public void deleteTest(String name) {
        mapper.deleteMy(name);

    }

    @Override
    public Lover selectIndividual(String name) {
        Lover lover=mapper.selectIndividual(name);
        return lover;
    }

    @Override
    public List<Lover> selectSome(Integer age) {
        List<Lover> lists=new ArrayList<>();
        lists=mapper.selectSome(age);
        return lists;
    }

    @Override
    public void insertDao(Lover lover) {
        mapper.insertForm(lover);
    }
}
