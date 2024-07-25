package com.alves.cadastro_usuarios.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidaCpfDTOTest {

    @Test
    void validaCpfDTOTest(){
        ValidaCpfDTO validaCpfDTO = new ValidaCpfDTO();
        validaCpfDTO.setFormatted("teste");
        validaCpfDTO.setValid(true);

        ValidaCpfDTO validaCpfDTOAllArgs = new ValidaCpfDTO(false, "teste");

        assertEquals("teste", validaCpfDTO.getFormatted());
        assertTrue(validaCpfDTO.isValid());

        assertEquals("teste", validaCpfDTOAllArgs.getFormatted());
        assertFalse(validaCpfDTOAllArgs.isValid());
    }

}