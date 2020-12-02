package com.dtss.service;

import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.entity.Result;
import com.dtss.pojo.TravelItem;
import com.github.pagehelper.Page;

public interface TravelItemService {
    void add(TravelItem travelItem);


    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    TravelItem findItemById(Integer id);

    void edit();
}
