package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.entity.Result;
import com.dtss.pojo.OrderSetting;
import com.dtss.service.OrderSettingService;
import com.dtss.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile) throws IOException {
        List<String[]> excel = POIUtils.readExcel(excelFile);

        List<OrderSetting> list = new ArrayList<>();

        for (String[] strings : excel) {
            OrderSetting orderSetting = new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
            list.add(orderSetting);
        }
        orderSettingService.add(list);

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);

    }




    @RequestMapping("/createData")
    public Result createData(String currentDate){
        List<Map> list = orderSettingService.createData(currentDate);
        System.out.println(list);
        return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,list);

    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        orderSettingService.editNumberByDate(orderSetting);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }
}
