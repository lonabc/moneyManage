package com.example.yinfuxi.Dao;

import com.example.yinfuxi.Lover;
import java.util.List;


public interface DaoParent {
    public Lover daoMy(String name);
    public  List<Lover> daoAll();

    public void deleteTest(String name);

    public Lover selectIndividual(String name);

    public List<Lover> selectSome(Integer age);

    public void insertDao(Lover lover);

}
