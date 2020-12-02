package com.dtss.dao;

import com.dtss.pojo.Setmeal;
import com.dtss.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetMealDao {
    List<TravelGroup> findAll();

    void add(Setmeal setmeal);

    void insertTravelgroupIdsBySetmealId(Map<String, Integer> map);

    Page<Setmeal> findPage(String queryString);
}
