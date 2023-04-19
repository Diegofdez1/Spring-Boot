package com.malaga42.demo.database.service;

import com.malaga42.demo.database.entity.Member;
import org.springframework.data.domain.Page;

public interface MemberService {

    Page<Member> getMembers(int currentPage, int paginationLimit);

    Member saveMember(String name);

    Member updateMember(Member member, String newName);

    void deleteByUuid(String uuid);

    Member findByUuid(String uuid);

    boolean existsByName(String name);
}
