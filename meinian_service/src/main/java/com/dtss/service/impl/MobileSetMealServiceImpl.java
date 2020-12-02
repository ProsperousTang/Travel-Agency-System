package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.MobileSetMealDao;
import com.dtss.pojo.Setmeal;
import com.dtss.service.MobileSetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = MobileSetMealService.class)
@Transactional
public class MobileSetMealServiceImpl implements MobileSetMealService {


    @Autowired
    MobileSetMealDao mobileSetMealDao;

    @Override
    public List<Setmeal> getSetmeal() {
       return mobileSetMealDao.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
       return mobileSetMealDao.findById(id);
    }
}
