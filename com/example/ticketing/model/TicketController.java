package com.example.ticketing.model;

import com.example.ticketing.model.Ticket;
import com.example.ticketing.service.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketPool ticketPool;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketPool.getAllTickets();
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket() {
        return ticketPool.purchaseTicket();
    }

    @PostMapping("/add/{count}")
    public void addTickets(@PathVariable int count) {
        ticketPool.addTickets(count);
    }
}
