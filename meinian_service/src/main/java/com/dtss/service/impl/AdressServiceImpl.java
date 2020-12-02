package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.AddressDao;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.pojo.Address;
import com.dtss.service.AdressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = AdressService.class)
@Transactional
public class AdressServiceImpl implements AdressService {


    @Autowired
    AddressDao addressDao;

    @Override
    public List<Address> findAllMaps() {
        return addressDao.findAllMaps();
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Address> pages = addressDao.findPage(queryPageBean.getQueryString());

        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public void addAddress(Address address) {
        addressDao.addAddress(address);
    }

    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);
    }
}
