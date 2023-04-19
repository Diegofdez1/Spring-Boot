package com.malaga42.demo.database.service;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Page<Member> getMembers(int currentPage, int paginationLimit) {
        PageRequest pageRequest = PageRequest.of(currentPage, paginationLimit);
        return memberRepository.findAll(pageRequest);
    }

    @Override
    public Member saveMember(String name) {
        if (existsByName(name)) {
            return null;
        }
        Member member = new Member();
        member.setName(name);
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member, String newName) {
        member.setName(newName);
        return memberRepository.save(member);
    }

    @Override
    public void deleteByUuid(String uuid) {
        memberRepository.deleteByUuid(uuid);
    }

    @Override
    public Member findByUuid(String uuid) {
        return memberRepository.findByUuid(uuid);
    }

    @Override
    public boolean existsByName(String name) {
        return memberRepository.existsByName(name);
    }

}
