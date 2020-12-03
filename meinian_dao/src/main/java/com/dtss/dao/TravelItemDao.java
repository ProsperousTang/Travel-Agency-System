package com.dtss.dao;

import com.dtss.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);


    Page<TravelItem> findPage(String queryString);

    void delete(Integer id);

    Integer countId(Integer id);

    TravelItem findItemById(Integer id);

    List<TravelItem> findTravelItem(Integer id);

    void edit(TravelItem travelItem);
}
