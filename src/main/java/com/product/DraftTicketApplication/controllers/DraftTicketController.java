package com.product.DraftTicketApplication.controllers;

import com.product.DraftTicketApplication.entities.RequestEntityDraftTicket;
import com.product.DraftTicketApplication.entities.ResponseEntityTickets;
import com.product.DraftTicketApplication.interfaces.TicketInterface;
import com.product.DraftTicketApplication.services.DraftTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DraftTicketController {
    private final TicketInterface ticketInterface;

    @Autowired
    public DraftTicketController(TicketInterface ticketInterface) {
        this.ticketInterface = ticketInterface;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntityTickets hello(@Valid @RequestBody RequestEntityDraftTicket draftTicket) {
        return ticketInterface.processDraftTicket(draftTicket);
    }
}


