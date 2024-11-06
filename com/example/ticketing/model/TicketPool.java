package com.example.ticketing.model;

import com.example.ticketing.model.Ticket;
import com.example.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketPool {
    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket purchaseTicket() {
        lock.lock();
        try {
            List<Ticket> tickets = ticketRepository.findAll();
            for (Ticket ticket : tickets) {
                if (ticket.isAvailable()) {
                    ticket.setAvailable(false);
                    ticketRepository.save(ticket);
                    return ticket;
                }
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void addTickets(int count) {
        lock.lock();
        try {
            for (int i = 0; i < count; i++) {
                ticketRepository.save(new Ticket());
            }
        } finally {
            lock.unlock();
        }
    }
}
