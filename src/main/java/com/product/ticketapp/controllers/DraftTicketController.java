package com.product.ticketapp.controllers;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.DraftTicketResponse;
import com.product.ticketapp.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DraftTicketController {
  private final TicketService ticketService;

  @Autowired
  public DraftTicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @PostMapping("/")
  @ResponseBody
  public DraftTicketResponse generateTickets(@Valid @RequestBody DraftTicketRequest draftTicket) {
    return ticketService.processDraftTicket(draftTicket);
  }
}
