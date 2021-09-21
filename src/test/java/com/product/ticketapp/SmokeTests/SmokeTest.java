package com.product.ticketapp.SmokeTests;

import com.product.ticketapp.controllers.DraftTicketController;
import com.product.ticketapp.interfaces.TicketService;
import com.product.ticketapp.services.DraftTicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

  @Autowired private DraftTicketController controller;
  @Autowired private TicketService ticketInterface;
  @Autowired private DraftTicketServiceImpl service;

  @Test
  public void contextLoads() {
    assertThat(controller).isNotNull();
    assertThat(ticketInterface).isNotNull();
    assertThat(service).isNotNull();
  }
}
