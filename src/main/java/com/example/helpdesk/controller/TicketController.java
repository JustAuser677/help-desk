package com.example.helpdesk.controller;

import com.example.helpdesk.model.TicketStatus;
import com.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketController {
    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }

    @GetMapping("/tickets")
    public String tickets(Model model){
        model.addAttribute("tickets", ticketRepository.findAllByOrderByCreatedAtDesc());
        return "tickets";
    }

    //Улучшил систему фильтрации: теперь можно фильтровать не только по NEW, но и по остальным статусам
    @GetMapping("/tickets/status/{status}")
    public String ticketsNew(Model model, @PathVariable String status){
        model.addAttribute("tickets", ticketRepository.findByStatus(TicketStatus.valueOf(status.toUpperCase())));
        return "tickets";
    }

    @GetMapping("/tickets/name/{name}")
    public String ticketsByName(Model model, @PathVariable String name){
        model.addAttribute("tickets", ticketRepository.findByCustomerNameContainingIgnoreCase(name));
        return "tickets";
    }
}
