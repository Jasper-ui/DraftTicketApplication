package com.product.DraftTicketApplication.SmokeTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.product.DraftTicketApplication.controllers.DraftTicketController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private DraftTicketController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}