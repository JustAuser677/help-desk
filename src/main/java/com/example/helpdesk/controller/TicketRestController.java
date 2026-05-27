package com.example.helpdesk.controller;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketService.saveTicket(ticket);
    }
}
