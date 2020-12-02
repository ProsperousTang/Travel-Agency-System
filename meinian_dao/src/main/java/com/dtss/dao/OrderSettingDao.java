package com.dtss.dao;

import com.dtss.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    void add(OrderSetting orderSetting);

    Integer countItem(Date orderDate);

    void updateItem(OrderSetting orderSetting);

    List<OrderSetting> createData(Map<String, Object> map);

    OrderSetting findAllByDate(Date date);


    void editReservation(OrderSetting orderSetting);
}
