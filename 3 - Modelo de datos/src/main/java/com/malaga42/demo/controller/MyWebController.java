package com.malaga42.demo.controller;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/web")
@AllArgsConstructor
public class MyWebController {

    private final MemberService memberService;

    public static Log log = LogFactory.getLog(MyWebController.class);

    @GetMapping
    public ModelAndView getMembers(Model model) {
        ModelAndView modelAndView = new ModelAndView("members_template");
        try {
            List<Member> members = memberService.getMembers();

            modelAndView.addAllObjects(model.asMap());
            modelAndView.addObject("membersList", members);

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
        Member member = memberService.findById(id);

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
            memberService.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        return redirectedView;
    }

}
