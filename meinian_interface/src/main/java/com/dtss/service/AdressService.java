package com.dtss.service;

import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.Address;

import java.util.List;

public interface AdressService {
    List<Address> findAllMaps();

    PageResult findPage(QueryPageBean queryPageBean);

    void addAddress(Address address);

    void deleteById(Integer id);
}
