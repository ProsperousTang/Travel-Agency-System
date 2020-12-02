package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.constant.RedisConstant;
import com.dtss.dao.SetMealDao;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.Setmeal;
import com.dtss.pojo.TravelGroup;
import com.dtss.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass =SetmealService.class )
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetMealDao setMealDao;

    @Autowired
    JedisPool jedisPool;
    @Override
    public List<TravelGroup> findAll() {
       List<TravelGroup> list =  setMealDao.findAll();
       return list;
    }

    @Override
    public void add(Setmeal setmeal,Integer[] travelgroupIds) {
        setMealDao.add(setmeal);

        setTravelgroupIdsBySetmealId(travelgroupIds,setmeal.getId());

        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> page = setMealDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    private void setTravelgroupIdsBySetmealId(Integer[] travelgroupIds, Integer id) {
        if (travelgroupIds!=null && travelgroupIds.length>0){
            for (Integer travelgroupId : travelgroupIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("travelgroupId",travelgroupId);
                map.put("setMealId",id);
                setMealDao.insertTravelgroupIdsBySetmealId(map);
            }
        }
    }


}
