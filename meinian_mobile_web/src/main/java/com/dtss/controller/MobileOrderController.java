package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.constant.RedisMessageConstant;
import com.dtss.entity.Result;
import com.dtss.pojo.Order;
import com.dtss.service.MobileOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class MobileOrderController {

    @Autowired
    JedisPool jedisPool;

    @Reference
    MobileOrderService mobileOrderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String)map.get("telephone");
        String validateCode = (String)map.get("validateCode");

        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);

        if (codeInRedis==null && !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        Result result = null;
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result=  mobileOrderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
           result= new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }


            return result;





    }



}
