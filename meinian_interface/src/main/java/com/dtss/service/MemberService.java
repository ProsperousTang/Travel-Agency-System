package com.dtss.service;

import com.dtss.pojo.Member;

import java.util.List;

public interface MemberService {
    Member findAllByTele(String telephone);

    void add(Member member);

    List<Integer> findMemberCount(List<String> list);
}
