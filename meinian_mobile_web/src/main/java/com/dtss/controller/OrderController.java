package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.entity.Result;
import com.dtss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    JedisPool jedisPool;

    @Reference
    OrderService orderService;

    @RequestMapping("/findById")
    public Result findById(Integer id) throws Exception {

      Map map =  orderService.findById(id);
      return new Result(true, MessageConstant.ORDER_SUCCESS,map);
    }
}
