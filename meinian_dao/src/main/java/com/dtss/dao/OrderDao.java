package com.dtss.dao;

import com.dtss.pojo.Order;
import com.dtss.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    List<Order> findAllByThree(Order order);

    void add(Order order);

    Map findById(Integer id);

    List<Map<String, Object>> findMealCount();

    int getTodayOrderNumber(String today);

    int getTodayVisitsNumber(String today);

    int getThisWeekAndMonthOrderNumber(Map<String, Object> paramWeek);

    int getThisWeekAndMonthVisitsNumber(Map<String, Object> paramWeekVisit);

    List<Map<String, Object>> findHotSetmeal();
}
