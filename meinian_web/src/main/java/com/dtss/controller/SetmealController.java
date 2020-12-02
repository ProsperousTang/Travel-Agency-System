package com.dtss.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.constant.RedisConstant;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.entity.Result;
import com.dtss.pojo.Setmeal;
import com.dtss.pojo.TravelGroup;
import com.dtss.service.SetmealService;
import com.dtss.utils.QiniuUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        try {
            String originalFilename = imgFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String substring = originalFilename.substring(i);
            String filename = UUID.randomUUID().toString()+substring;

            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> list = setmealService.findAll();
        if (list!=null && list.size()>0){
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        }
        return new Result(false,MessageConstant.QUERY_SETMEALLIST_FAIL);
    }

    @RequestMapping("/add")
    public Result add(Integer[] travelgroupIds,@RequestBody Setmeal setmeal){
           setmealService.add(setmeal,travelgroupIds);

           return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =  setmealService.findPage(queryPageBean);
        System.out.println("pageresult: "+pageResult);
        return pageResult;
    }


}
