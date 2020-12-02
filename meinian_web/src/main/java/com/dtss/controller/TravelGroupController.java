package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.entity.Result;
import com.dtss.pojo.TravelGroup;
import com.dtss.pojo.TravelItem;
import com.dtss.service.TravelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    TravelGroupService travelGroupService;





    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelItem> list = travelGroupService.findAll();
        System.out.println("list"+list);
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS,list);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult =  travelGroupService.findPage(queryPageBean);
        System.out.println("pageresult:"+pageResult);
       return pageResult;
    }

    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        //System.out.println("travelItemIds: "+travelItemIds+"travelGroup: "+travelGroup);
        travelGroupService.add(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        TravelGroup travelGroup = travelGroupService.findById(id);
        System.out.println("travelGroup:"+travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS,travelGroup);
    }

    @RequestMapping("/findAll02")
    public List<TravelItem> findAll02(){
        List<TravelItem> list = travelGroupService.findAll();
        return list;

    }

    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public List findTravelItemIdByTravelgroupId(Integer id){
        List<Integer> list = travelGroupService.findTravelItemIdByTravelgroupId(id);
        return list;
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        //System.out.println("travelItemIds: "+travelItemIds+"travelGroup: "+travelGroup);
        travelGroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }

    @RequestMapping("/deleteRow")
    public Result deleteRow(Integer id){
        System.out.println("id "+id);

        travelGroupService.deleteRow(id);
        return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);


    }
}
