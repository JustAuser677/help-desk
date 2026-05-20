package com.example.helpdesk.controller;
import java.util.*;
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

        List<String> contacts = List.of(
                "support@mail.ru",
                "+7 (926) 349-67-55",
                "Telegram: @support",
                "ВК: @support"
        );

        model.addAttribute("pageTitle", "Контакты поддержки");
        model.addAttribute("listOfSupport", contacts);
        return "contacts";
    }

    @GetMapping("/faq")
    public String faq(Model model){
        model.addAttribute("pageTitle","ЧАВО");

        return "faq";
    }

    @GetMapping("/etc")
    public String etc(Model model){
        model.addAttribute("pageTitle", "Дополнительно");
        return "etc";
    }
}
