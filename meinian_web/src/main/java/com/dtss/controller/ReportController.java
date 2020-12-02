package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.entity.Result;
import com.dtss.service.MemberService;
import com.dtss.service.OrderService;
import com.dtss.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Reference
    ReportService reportService;

    @Reference
    OrderService orderService;

    @Reference
    MemberService memberService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH,-12);



        List<String> list = new ArrayList<String>();


        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
            list.add(new SimpleDateFormat("YYYY-MM").format(calendar.getTime()));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("months",list);

       List<Integer> nums =  memberService.findMemberCount(list);
       map.put("memberCount",nums);

       return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }


    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
         List<Map<String,Object>> list = orderService.findMealCount();
         Map<String,Object> data = new HashMap<>();
         data.put("setmealCount",list);
         List<String> setmealNames = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String name = (String) map.get("name");
            setmealNames.add(name);

        }
        data.put("setmealNames",setmealNames);
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,data);
    }


    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
      Map<String,Object> map =   reportService.getBusinessReportData();
      return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,map);
    }
}
