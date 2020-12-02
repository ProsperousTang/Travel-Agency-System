package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.TravelGroupDao;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.TravelGroup;
import com.dtss.pojo.TravelItem;
import com.dtss.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    TravelGroupDao travelGroupDao;

    @Override
    public List<TravelItem> findAll() {
       List<TravelItem> list =  travelGroupDao.findAll();
       return list;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       Page<TravelGroup> page = travelGroupDao.findPage(queryPageBean.getQueryString());
       return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);

        setGroupidAndItemid(travelItemIds,travelGroup.getId());
    }

    @Override
    public TravelGroup findById(Integer id) {
        TravelGroup travelGroup = travelGroupDao.findById(id);
        return travelGroup;
    }

    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id) {
        List<Integer> list = travelGroupDao.findTravelItemIdByTravelgroupId(id);
        return list;
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        travelGroupDao.deleteBefore(travelGroup.getId());
        setGroupidAndItemid(travelItemIds,travelGroup.getId());
    }

    @Override
    public void deleteRow(Integer id) {
      Integer i =  travelGroupDao.countId(id);
      Integer j = travelGroupDao.countIdOnsetmeal(id);
        if (i!=null && i>0){
            travelGroupDao.deleteBefore(id);
        }
        if (j!=null && j>0){
            travelGroupDao.deleteIdOnsetmeal(id);
        }
        travelGroupDao.deleteRow(id);
    }

    private void setGroupidAndItemid(Integer[] travelItemIds, Integer id) {
        if (travelItemIds.length>0 && travelItemIds!=null){

            for (Integer travelItemId : travelItemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("travelItemId",travelItemId);
                map.put("travelGroupId",id);
                travelGroupDao.setGroupidAndItemid(map);
            }

        }
    }
}
