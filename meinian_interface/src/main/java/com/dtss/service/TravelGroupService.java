package com.dtss.service;

import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.TravelGroup;
import com.dtss.pojo.TravelItem;

import java.util.List;

public interface TravelGroupService {
    List<TravelItem> findAll();

    PageResult findPage(QueryPageBean queryPageBean);

    void add(Integer[] travelItemIds, TravelGroup travelGroup);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    void deleteRow(Integer id);


}
