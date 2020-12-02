package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.AddressDao;
import com.dtss.pojo.Address;
import com.dtss.service.AdressService;
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
}
