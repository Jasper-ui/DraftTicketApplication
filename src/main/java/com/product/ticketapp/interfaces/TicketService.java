package com.product.ticketapp.interfaces;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.DraftTicketResponse;

public interface TicketService {
  DraftTicketResponse processDraftTicket(DraftTicketRequest draftTicket);
}
