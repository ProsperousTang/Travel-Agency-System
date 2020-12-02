package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.OrderDao;
import com.dtss.service.OrderService;
import com.dtss.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;


    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderDao.findById(id);

        if (map!=null){
            Date date =(Date) map.get("orderDate");
            DateUtils.parseDate2String(date);
            return map;
        }
      return map;
    }

    @Override
    public List<Map<String, Object>> findMealCount() {
        return orderDao.findMealCount();
    }
}
