package com.dtss.dao;

import com.dtss.pojo.TravelGroup;
import com.dtss.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {

    List<TravelItem> findAll();

    Page<TravelGroup> findPage(String queryString);

    void add(TravelGroup travelGroup);

    void setGroupidAndItemid(Map<String, Integer> map);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void deleteBefore(Integer travelGroupId);

    void edit(TravelGroup travelGroup);

    void deleteRow(Integer id);

    Integer countId(Integer id);

    Integer countIdOnsetmeal(Integer id);

    void deleteIdOnsetmeal(Integer id);

    List<TravelGroup> findTravelGroup(Integer id);
}
