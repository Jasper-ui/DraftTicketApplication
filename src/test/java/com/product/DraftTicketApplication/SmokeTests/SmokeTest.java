package com.product.DraftTicketApplication.SmokeTests;

import com.product.DraftTicketApplication.controllers.DraftTicketController;
import com.product.DraftTicketApplication.interfaces.TicketInterface;
import com.product.DraftTicketApplication.services.DraftTicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private DraftTicketController controller;
    @Autowired
    private TicketInterface ticketInterface;
    @Autowired
    private DraftTicketService service;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(ticketInterface).isNotNull();
        assertThat(service).isNotNull();
    }
}