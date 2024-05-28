package com.example.yinfuxi.Service;
import com.example.yinfuxi.Dao.DaoParent;
import com.example.yinfuxi.Lover;
import com.example.yinfuxi.Mapper.Mapper;
import com.example.yinfuxi.StaticMy.Emp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMy implements ServiceParent{
    @Autowired
    DaoParent daoParent;
    @Autowired
    Mapper mapper;

//    @CachePut(cacheNames = "userCacheMy",key="#name")
    public Lover serviceMy(String name)
    {
       Lover lover =daoParent.daoMy(name);
       return lover;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Lover> selectAll() {
        List<Lover> list=daoParent.daoAll();
        return list;
    }

    public List<Lover> selectTest(int pageStart , int pageSize)
    {
        PageHelper.startPage(pageStart,pageSize);
        List<Lover> list=daoParent.daoAll();
        Page<Lover> p=(Page<Lover>) list;
        System.out.println("test+Page插件"+p.getResult());
        return p.getResult();
    }

    @Override
    public void deleteTest(String name) {
        daoParent.deleteTest(name);

    }

    @Override
    public Lover selectIndividual(String name) {
        return daoParent.selectIndividual(name);
    }

    @Override
    public List<Lover> selectSome(Integer age) {
        List<Lover> lists=new ArrayList<>();
        lists=daoParent.selectSome(age);
        return lists;
    }

    @Override
    public void insertForm(Lover lover) {
        daoParent.insertDao(lover);
    }

    @Override
    public void update(Lover lover) {
        mapper.update(lover);
    }

    @Transactional(rollbackFor=RuntimeException.class)//spring事务管理，运行时出错，回滚事务
    public Emp loginTest(Emp e) {
       Emp testemp= mapper.login(e);
        return testemp;
    }

    @Transactional(rollbackFor=RuntimeException.class,propagation = Propagation.REQUIRES_NEW)//spring事务管理，运行时出错，回滚事务
    public Integer sumService()
    {
       return mapper.sumItem();
    }



}
