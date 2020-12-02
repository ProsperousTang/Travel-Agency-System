package com.dtss.service;

import com.dtss.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void add(List<OrderSetting> list);

    List<Map> createData(String currentDate);

    void editNumberByDate(OrderSetting orderSetting);
}
