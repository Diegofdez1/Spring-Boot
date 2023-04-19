package com.malaga42.demo.database.service;

import com.malaga42.demo.database.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    Member saveMember(String name);

    Member updateMember(Member member, String newName);

    void deleteById(String id);

    Member findById(String id);
}
