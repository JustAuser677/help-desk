package com.example.helpdesk.service;

import com.example.helpdesk.dto.TicketCreateDto;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    Ticket createTicket(TicketCreateDto ticketCreateDto);

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);
}
