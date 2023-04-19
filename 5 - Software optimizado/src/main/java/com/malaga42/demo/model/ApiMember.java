package com.malaga42.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiMember {

    private int pageSize;

    private long totalElements;

    private List<MemberDTO> data;
}
