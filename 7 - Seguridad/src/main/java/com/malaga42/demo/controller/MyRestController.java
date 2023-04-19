package com.malaga42.demo.controller;

import com.malaga42.demo.config.RequestsLimitation;
import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.service.MemberService;
import com.malaga42.demo.mapper.MemberMapper;
import com.malaga42.demo.model.ApiMember;
import com.malaga42.demo.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Slf4j
public class MyRestController {

    private final MemberService memberService;

    private final MemberMapper memberMapper;

    private final RequestsLimitation requestsLimitation;

    @Value("${my.pagination.limit}")
    private int paginationLimit;

    @GetMapping
    public ResponseEntity<ApiResponse<ApiMember>> getMembers(@RequestParam(defaultValue = "1") String page) {
        try {
            if (requestsLimitation.getBucket().tryConsume(1)) {
                int currentPage = Integer.parseInt(page) - 1;

                PageRequest pageRequest = PageRequest.of(currentPage, paginationLimit);
                Page<Member> pageMembers = memberService.getMembers(pageRequest);

                return new ResponseEntity<>(new ApiResponse<>(200, new ApiMember(paginationLimit, pageMembers.getTotalElements(), memberMapper.toDTO(pageMembers.getContent()))), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse<>(702, null), HttpStatus.TOO_MANY_REQUESTS);
            }

        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ApiResponse<>(500, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createMember(@RequestParam(name = "name") String name) {
        try {
            if (requestsLimitation.getBucket().tryConsume(1)) {
                if (memberService.existsByName(name)) {
                    return new ResponseEntity<>(new ApiResponse<>(701, "Ya existe un miembro con ese nombre"), HttpStatus.OK);
                }
                memberService.saveMember(name);
            } else {
                return new ResponseEntity<>(new ApiResponse<>(702, null), HttpStatus.TOO_MANY_REQUESTS);
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ApiResponse<>(500, "Ha ocurrido un problema a la hora de crear Member con nombre " + name), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ApiResponse<>(200, "Member con nombre '" + name + "' creado con éxito"), HttpStatus.OK);
    }

}
