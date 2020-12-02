package com.dtss.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.TravelItemDao;
import com.dtss.entity.PageResult;
import com.dtss.entity.QueryPageBean;
import com.dtss.entity.Result;
import com.dtss.pojo.TravelItem;
import com.dtss.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 有问题
 */
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService{


    @Autowired
    private TravelItemDao travelItemDao;
    @Override
    public void add(TravelItem travelItem) {
      travelItemDao.add(travelItem);
    }


    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<TravelItem> page = travelItemDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) {
        Integer i = travelItemDao.countId(id);
        if (i>0){
            throw new RuntimeException("can't delete");
        }
        travelItemDao.delete(id);
    }

    @Override
    public TravelItem findItemById(Integer id) {
       return travelItemDao.findItemById(id);
    }

    @Override
    public void edit() {
//        travelItemDao.edit();
    }
}
