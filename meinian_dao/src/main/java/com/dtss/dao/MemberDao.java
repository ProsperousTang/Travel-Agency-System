package com.dtss.dao;

import com.dtss.pojo.Member;

public interface MemberDao {
    Member findAllByTele(String telephone);

    void add(Member member);

    Integer findMemberCount(String regTime);

    int getTodayNewMember(String today);

    int getTotalMember();

    int getThisWeekAndMonthNewMember(String weekMonday);
}
