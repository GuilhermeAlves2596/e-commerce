package com.alves.cep.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDTOTest {
    @InjectMocks
    ResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        responseDTO.setCep(new ViaCepResponseDTO("teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste"));
        responseDTO.setErro("teste");
    }

    @Test
    void getCep() {
        ViaCepResponseDTO dto = new ViaCepResponseDTO("teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste");
        assertEquals(dto.getCep(), responseDTO.getCep().getCep());
    }

    @Test
    void getErro() {
        assertEquals("teste", responseDTO.getErro());
    }

    @Test
    void setCep() {
        responseDTO.setCep(new ViaCepResponseDTO("teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2"));
        assertEquals("teste2", responseDTO.getCep().getBairro());
    }

    @Test
    void setErro() {
        responseDTO.setErro("teste2");
        assertEquals("teste2", responseDTO.getErro());
    }

    @Test
    void noArgs(){
        ResponseDTO dto = new ResponseDTO();
        assertNotNull(dto);
    }

    @Test
    void viaCepConstrutor(){
        ResponseDTO dto = new ResponseDTO(new ViaCepResponseDTO("teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2","teste2"));
        assertEquals("teste2", dto.getCep().getBairro());
    }

    @Test
    void erroConstrutor(){
        ResponseDTO dto = new ResponseDTO("teste");
        assertEquals("teste", dto.getErro());
    }
}