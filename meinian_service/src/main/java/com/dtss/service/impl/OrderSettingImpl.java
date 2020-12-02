package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.OrderSettingDao;
import com.dtss.pojo.OrderSetting;
import com.dtss.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingImpl implements OrderSettingService {

    @Autowired
    OrderSettingDao orderSettingDao;
    @Override
    public void add(List<OrderSetting> list) {
        for (OrderSetting orderSetting : list) {
           Integer i =  orderSettingDao.countItem(orderSetting.getOrderDate());
           if (i!=null && i>0){
               orderSettingDao.updateItem(orderSetting);
           }else
              orderSettingDao.add(orderSetting);
        }
    }

    // this.leftobj = [
    //     { date: 1, number: 120, reservations: 1 },
    //     { date: 3, number: 120, reservations: 1 },
    //     { date: 4, number: 120, reservations: 120 },
    //     { date: 6, number: 120, reservations: 1 },
    //     { date: 8, number: 120, reservations: 1 }
    // ];
    @Override
    public List<Map> createData(String currentDate) {
        String startDate = currentDate+"-01";
        String endDate = currentDate+"-31";
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        List<OrderSetting> list = orderSettingDao.createData(map);

        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map<String,Object> mapFinal = new HashMap<>();
            mapFinal.put("date",orderSetting.getOrderDate().getDate());
            mapFinal.put("number",orderSetting.getNumber());
            mapFinal.put("reservations",orderSetting.getReservations());
            data.add(mapFinal);
        }
        return data;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        orderSettingDao.updateItem(orderSetting);
    }
}
