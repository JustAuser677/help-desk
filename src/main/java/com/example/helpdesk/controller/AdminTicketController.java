package com.example.helpdesk.controller;

import com.example.helpdesk.model.TicketStatus;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/tickets")
public class AdminTicketController {

    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public AdminTicketController(TicketService ticketService, TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/status/{status}")
    public String ticketsNew(Model model, @PathVariable String status){
        model.addAttribute("tickets", ticketRepository.findByStatus(TicketStatus.valueOf(status.toUpperCase())));
        return "admin/tickets";
    }

    @GetMapping("/name/{name}")
    public String ticketsByName(Model model, @PathVariable String name){
        model.addAttribute("tickets", ticketRepository.findByCustomerNameContainingIgnoreCase(name));
        return "admin/tickets";
    }

    @GetMapping
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "admin/tickets";
    }
}


