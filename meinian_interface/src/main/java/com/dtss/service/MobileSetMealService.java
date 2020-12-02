package com.dtss.service;

import com.dtss.pojo.Setmeal;

import java.util.List;

public interface MobileSetMealService {
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
