package com.alves.cadastro_usuarios.service.Bcrypt;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BcryptServiceTest {

    @InjectMocks
    BcryptService bcryptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void encoder() {
        String senhaHash = bcryptService.encoder("senha");
        assertNotEquals("senha", senhaHash);
    }

    @Test
    void matches() {
        boolean result = bcryptService.matches("senha", "asd");
        assertFalse(result);
    }
}