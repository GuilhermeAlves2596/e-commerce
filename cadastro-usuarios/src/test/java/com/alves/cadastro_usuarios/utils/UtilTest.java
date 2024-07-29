package com.alves.cadastro_usuarios.utils;

import org.junit.jupiter.api.Test;

import static com.alves.cadastro_usuarios.utils.Util.isNullOrBlank;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

    @Test
    void testIsNullOrBlankTrue(){
        assertTrue(isNullOrBlank(""));
    }

    @Test
    void testIsNullOrBlankFalse(){
        assertFalse(isNullOrBlank("123"));
    }
}