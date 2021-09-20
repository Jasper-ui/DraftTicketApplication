package com.product.DraftTicketApplication.interfaces;

import com.product.DraftTicketApplication.entities.RequestEntityDraftTicket;
import com.product.DraftTicketApplication.entities.ResponseEntityTickets;

public interface TicketInterface {
    ResponseEntityTickets processDraftTicket(RequestEntityDraftTicket draftTicket);
}
