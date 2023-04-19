package com.malaga42.demo.mapper;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.model.MemberDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO toDTO(Member member);

    List<MemberDTO> toDTO(List<Member> memberList);

}