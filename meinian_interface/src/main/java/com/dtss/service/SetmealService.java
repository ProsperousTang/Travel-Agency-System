package com.dtss.service;

import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.Setmeal;
import com.dtss.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    List<TravelGroup> findAll();

    void add(Setmeal setmeal,Integer[] travelgroupIds);


    PageResult findPage(QueryPageBean queryPageBean);
}
