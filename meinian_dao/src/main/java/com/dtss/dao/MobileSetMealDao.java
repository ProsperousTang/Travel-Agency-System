package com.dtss.dao;

import com.dtss.pojo.Setmeal;

import java.util.List;

public interface MobileSetMealDao {
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
