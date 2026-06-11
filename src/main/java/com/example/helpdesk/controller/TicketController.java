package com.example.helpdesk.controller;

import com.example.helpdesk.dto.TicketCreateDto;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.TicketStatus;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;

    public TicketController(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }

    @GetMapping()
    public String tickets(Model model){
        model.addAttribute("tickets", ticketRepository.findAllByOrderByCreatedAtDesc());
        return "redirect:/admin/tickets";
    }

    //Улучшил систему фильтрации: теперь можно фильтровать не только по NEW, но и по остальным статусам
    @GetMapping("/status/{status}")
    public String ticketsNew(Model model, @PathVariable String status){
        model.addAttribute("tickets", ticketRepository.findByStatus(TicketStatus.valueOf(status.toUpperCase())));
        return "redirect:/admin/tickets/status/"+status.toUpperCase();
    }

    @GetMapping("/name/{name}")
    public String ticketsByName(Model model, @PathVariable String name){
        model.addAttribute("tickets", ticketRepository.findByCustomerNameContainingIgnoreCase(name));
        return "redirect:/admin/tickets/name/"+name;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("ticket", new TicketCreateDto());
        return "ticket-form";
    }

    @PostMapping
    public String createTicket(
            @Valid @ModelAttribute("ticket") TicketCreateDto ticketCreateDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ticket-form";
        }

        Ticket savedTicket = ticketService.createTicket(ticketCreateDto);
        return "redirect:/tickets/" + savedTicket.getId() + "/success";
    }

    @GetMapping("/{id}/success")
    public String showSuccessPage(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-success";
    }
}
