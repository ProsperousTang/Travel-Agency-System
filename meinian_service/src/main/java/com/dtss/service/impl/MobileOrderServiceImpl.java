package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.constant.MessageConstant;
import com.dtss.dao.MemberDao;
import com.dtss.dao.OrderDao;
import com.dtss.dao.OrderSettingDao;
import com.dtss.entity.Result;
import com.dtss.pojo.Member;
import com.dtss.pojo.Order;
import com.dtss.pojo.OrderSetting;
import com.dtss.service.MobileOrderService;
import com.dtss.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = MobileOrderService.class)
@Transactional
public class MobileOrderServiceImpl implements MobileOrderService {
//1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约

//2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约

//3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约

//4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约

//5、预约成功，更新当日的已预约人数


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public Result order(Map map) throws Exception {
        String orderDate =(String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingDao.findAllByDate(date);

        if (orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }else {
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            if (reservations>=number){
                return new Result(false,  MessageConstant.ORDER_FULL);
            }
        }

        String telephone = (String)map.get("telephone");
        Member member = memberDao.findAllByTele(telephone);

        if (member!=null){
            String setmealId =(String) map.get("setmealId");
            Order order = new Order(member.getId(),date,null,null,Integer.parseInt(setmealId));

            List<Order> list = orderDao.findAllByThree(order);
            if (list!=null && list.size()>0){
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }else {
            member = new Member((String)map.get("name"),(String)map.get("sex"),(String)map.get("idCard"),telephone,new Date());

            memberDao.add(member);
        }

        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservation(orderSetting);

        Order order = new Order(member.getId(),new Date(),(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")));
        orderDao.add(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order);



    }
}
