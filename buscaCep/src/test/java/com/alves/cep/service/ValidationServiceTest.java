package com.alves.cep.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    @InjectMocks
    ValidationService validationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void isValidCepTrue(){
        assertTrue(validationService.isValidCep("86300000"));
        assertFalse(validationService.isValidCep(null));
        assertFalse(validationService.isValidCep(""));
        assertFalse(validationService.isValidCep("123"));
    }

}