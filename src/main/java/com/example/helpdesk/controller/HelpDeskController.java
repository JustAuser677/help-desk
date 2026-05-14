package com.example.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpDeskController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pageTitle", "Главная Help Desk");
        model.addAttribute("welcome",
                "Добро пожаловать на главную страницу техподдержки");
        return "index";

    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("pageTitle", "О нас");
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts(Model model){
        model.addAttribute("pageTitle", "Контакты поддержки");
        model.addAttribute("supportEmail", "support@mail.ru");
        model.addAttribute("workTime","Пн-Пт, 9:00-18:00");
        model.addAttribute("phoneNumber", "+7 (228) 1337-67-55");
        return "contacts";
    }
}
