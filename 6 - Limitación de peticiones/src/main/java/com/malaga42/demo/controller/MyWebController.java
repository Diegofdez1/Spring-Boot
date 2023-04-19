package com.malaga42.demo.controller;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.service.MemberService;
import com.malaga42.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
@Slf4j
public class MyWebController {

    private final MemberService memberService;

    private final MemberMapper memberMapper;

    @Value("${my.pagination.limit}")
    private int paginationLimit;

    @GetMapping
    public ModelAndView getMembers(@RequestParam(defaultValue = "1") String page) {
        ModelAndView modelAndView = new ModelAndView("members_template");
        try {
            int currentPage = Integer.parseInt(page) - 1;

            Page<Member> pageMembers = memberService.getMembers(currentPage, paginationLimit);
            int totalPages = pageMembers.getTotalPages();

            List<Integer> pages;
            if (totalPages > 0) {
                pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            } else {
                pages = new ArrayList<>();
            }

            modelAndView.addObject("membersList", memberMapper.toDTO(pageMembers.getContent()));
            modelAndView.addObject("pagesList", pages);
            modelAndView.addObject("currentPage", currentPage + 1);

        } catch (Exception ex) {
            log.error(ex.toString());
        }

        return modelAndView;
    }

    @PostMapping
    public RedirectView createMember(@RequestParam(name = "name") String name) {
        RedirectView redirectedView = new RedirectView("/web");
        try {
            memberService.saveMember(name);
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return redirectedView;
    }

    @PostMapping(value = "/update")
    public RedirectView updateMember(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name) {

        RedirectView redirectedView = new RedirectView("/web");
        Member member = memberService.findByUuid(id);

        try {
            memberService.updateMember(member, name);
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        return redirectedView;
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteMember(@RequestParam(name = "id") String id) {

        RedirectView redirectedView = new RedirectView("/web");

        try {
            memberService.deleteByUuid(id);
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        return redirectedView;
    }

}
