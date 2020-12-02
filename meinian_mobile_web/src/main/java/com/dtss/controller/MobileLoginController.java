package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.constant.RedisMessageConstant;
import com.dtss.entity.Result;
import com.dtss.pojo.Member;
import com.dtss.service.MemberService;
import com.dtss.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class MobileLoginController {
    @Autowired
    JedisPool jedisPool;

    @Reference
    MemberService memberService;

    @RequestMapping("/check")
    public Result check(@RequestBody Map map, HttpServletResponse response){
        String telephone = (String) map.get("telephone");
        String code = (String) map.get("validateCode");
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (codeInRedis==null || !code.equals(codeInRedis)){
                 return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Member member = memberService.findAllByTele(telephone);
        if (member==null){
            member = new Member(telephone,new Date());
            memberService.add(member);
        }
        Cookie cookie = new Cookie("login_member_telephone",telephone);
        cookie.setMaxAge(60*60*24*30);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new Result(true,MessageConstant.LOGIN_SUCCESS);

    }
}
