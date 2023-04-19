package com.malaga42.demo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @RequestMapping(value = {"/", ""})
    public RedirectView home() {
        return new RedirectView("/login");
    }

    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (isUserAuthenticated(auth)) {
                return new ModelAndView(new RedirectView("/web"));
            }

        } catch (Exception ex) {
            log.error("ERROR", ex);
        }
        return new ModelAndView("login");
    }

    public boolean isUserAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }
}