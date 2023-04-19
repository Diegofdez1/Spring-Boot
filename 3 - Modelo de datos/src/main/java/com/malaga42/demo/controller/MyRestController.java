package com.malaga42.demo.controller;

import com.malaga42.demo.database.entity.Member;
import com.malaga42.demo.database.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class MyRestController {

    private final MemberService memberService;

    public static Log log = LogFactory.getLog(MyRestController.class);

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getMembers();
    }

    @PostMapping
    public String createMember(@RequestParam(name = "name") String name) {
        try {
            Member member = memberService.saveMember(name);
            if (member == null) {
                return "Ya existe un miembro con ese nombre";
            }

        } catch (Exception ex) {
            log.error(ex.toString());
            return "Ha ocurrido un problema a la hora de crear el miembro con nombre " + name;
        }
        return "El miembro con nombre '" + name + "' creado con éxito";
    }

}
