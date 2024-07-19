package com.alves.cep.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class ViaCepResponseDTOTest {

    @InjectMocks
    ViaCepResponseDTO viaCepResponseDTO;

    @BeforeEach
    void setUp() {
        viaCepResponseDTO = new ViaCepResponseDTO();
    }

    @Test
    void getCep() {
        viaCepResponseDTO.setCep("teste");
        assertEquals("teste", viaCepResponseDTO.getCep());
    }

    @Test
    void getLogradouro() {
        viaCepResponseDTO.setLogradouro("teste");
        assertEquals("teste", viaCepResponseDTO.getLogradouro());
    }

    @Test
    void getComplemento() {
        viaCepResponseDTO.setComplemento("teste");
        assertEquals("teste", viaCepResponseDTO.getComplemento());
    }

    @Test
    void getUnidade() {
        viaCepResponseDTO.setUnidade("teste");
        assertEquals("teste", viaCepResponseDTO.getUnidade());
    }

    @Test
    void getBairro() {
        viaCepResponseDTO.setBairro("teste");
        assertEquals("teste", viaCepResponseDTO.getBairro());
    }

    @Test
    void getLocalidade() {
        viaCepResponseDTO.setLocalidade("teste");
        assertEquals("teste", viaCepResponseDTO.getLocalidade());
    }

    @Test
    void getUf() {
        viaCepResponseDTO.setUf("teste");
        assertEquals("teste", viaCepResponseDTO.getUf());
    }

    @Test
    void getIbge() {
        viaCepResponseDTO.setIbge("teste");
        assertEquals("teste", viaCepResponseDTO.getIbge());
    }

    @Test
    void getGia() {
        viaCepResponseDTO.setGia("teste");
        assertEquals("teste", viaCepResponseDTO.getGia());
    }

    @Test
    void getDdd() {
        viaCepResponseDTO.setDdd("teste");
        assertEquals("teste", viaCepResponseDTO.getDdd());
    }

    @Test
    void getSiafi() {
        viaCepResponseDTO.setSiafi("teste");
        assertEquals("teste", viaCepResponseDTO.getSiafi());
    }

    @Test
    void getErro() {
        viaCepResponseDTO.setErro("teste");
        assertEquals("teste", viaCepResponseDTO.getErro());
    }
}