package com.dtss.controller;

import com.dtss.constant.MessageConstant;
import com.dtss.constant.RedisMessageConstant;
import com.dtss.entity.Result;
import com.dtss.utils.SMSUtils;
import com.dtss.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;


@RestController
@RequestMapping("/validateCode")
public class ValidateLoginController {
    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/send4Login")
    public Result send4Order(String telephone){


        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            SMSUtils.sendShortMessage(telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("the code is "+code);
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}

