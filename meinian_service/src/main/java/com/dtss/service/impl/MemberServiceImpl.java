package com.dtss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dtss.dao.MemberDao;
import com.dtss.pojo.Member;
import com.dtss.service.MemberService;
import com.dtss.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;
    @Override
    public Member findAllByTele(String telephone) {
      return   memberDao.findAllByTele(telephone);
    }

    @Override
    public void add(Member member) {
          memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCount(List<String> list) {

        List<Integer> membercount = new ArrayList<>();
        for (String months : list) {
            String regTime = DateUtils.getLastDayOfMonth(months);
           Integer i =  memberDao.findMemberCount(regTime);
           membercount.add(i);
        }
        return membercount;


    }


//    public List<Integer> findMemberCountByMonth(List<String> months) {
//        List<Integer> memeberCountList = new ArrayList<Integer>();
//        if(monthsList!=null && monthsList.size()>0){
//            for (String months : monthsList) {
//
//                //String regTime = months+"-31";
//                // 获取指定月份的最后一天
//                String regTime =  DateUtils.getLastDayOfMonth(months);
//                //  迭代过去12个月，每个月注册会员的数量，根据注册日期查询
//                Integer memeberCount = memberDao.findMemberCountBeforeDate(regTime);
//                memeberCountList.add(memeberCount);
//            }
//        }
//        return memeberCountList;
//    }
}
