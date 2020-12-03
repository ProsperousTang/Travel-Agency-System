package com.dtss.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dtss.constant.MessageConstant;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.entity.Result;
import com.dtss.pojo.TravelItem;
import com.dtss.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/travelItem")
public class TravelItemController {

    @Reference//阿里的zookeeper和service注解，要上下一致
    TravelItemService travelItemService;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")
    public Result add(@RequestBody TravelItem travelItem){
        travelItemService.add(travelItem);
        //response.data
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

      PageResult pageResult =  travelItemService.findPage(queryPageBean);
     //   System.out.println("pageresult"+pageResult);
       return pageResult;

    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")
    public Result delete(Integer id){
     try{
         travelItemService.delete(id);
         return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
     }catch (RuntimeException e){
         return new Result(false,e.getMessage());
     }catch (Exception e){
         return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
     }

    }

    @RequestMapping("/findItemById")
    public Result findItemById(Integer id){
       TravelItem travelItem =  travelItemService.findItemById(id);
       return new Result(true,MessageConstant.ADD_TRAVELITEM_SUCCESS,travelItem);

    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

//    @RequestMapping("/edit")
//    public Result edit(){
//       travelItemService.edit();
//       return new Result(true,MessageConstant.EDIT_MEMBER_SUCCESS);
//
//    }
}
