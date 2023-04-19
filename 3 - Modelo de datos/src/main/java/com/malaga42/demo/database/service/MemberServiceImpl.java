package com.malaga42.demo.database.service;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member saveMember(String name) {
        List<Member> memberList = getMembers();
        for (Member member : memberList) {
            if (member.getName().equals(name)) {
                return null;
            }
        }

        Member member = new Member();
        member.setName(name);
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member oldMember, String newName) {
        List<Member> memberList = getMembers();
        for (Member member : memberList) {
            if (member.getName().equals(newName) && !oldMember.getId().equals(member.getId())) {
                return null;
            }
        }
        oldMember.setName(newName);
        return memberRepository.save(oldMember);
    }

    @Override
    public void deleteById(String id) {
        memberRepository.deleteById(new ObjectId(id));
    }

    @Override
    public Member findById(String id) {
        Optional<Member> member = memberRepository.findById(new ObjectId(id));
        return member.orElse(null);
    }

}
