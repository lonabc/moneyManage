package com.example.yinfuxi.Service;

import com.example.yinfuxi.Lover;

import java.util.List;


public interface ServiceParent {

    public Lover serviceMy(String name);
    public List<Lover> selectAll();

    public void deleteTest(String name);

    public Lover selectIndividual(String name);

    public List<Lover> selectSome(Integer age);

    public void insertForm(Lover lover);

    public void update(Lover lover);
}
