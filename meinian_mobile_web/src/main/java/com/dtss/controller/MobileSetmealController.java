package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference; //不用阿里的会报空指针异常
import com.dtss.constant.MessageConstant;
import com.dtss.entity.Result;
import com.dtss.pojo.Setmeal;
import com.dtss.service.MobileSetMealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class MobileSetmealController {


    @Reference
    MobileSetMealService mobileSetMealService;


    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
      List<Setmeal> list = mobileSetMealService.getSetmeal();
      return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
       Setmeal setmeal =  mobileSetMealService.findById(id);
       return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmeal);
    }
}
