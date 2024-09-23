package com.minglespace.controller.view;

import com.minglespace.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String indexPage(Model model){
        model.addAttribute("authenticated", SecurityUtils.isAuthenticated());
        return "pages/index";
    }
}
