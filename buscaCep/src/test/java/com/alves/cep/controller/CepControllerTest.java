package com.alves.cep.controller;

import com.alves.cep.dto.ViaCepResponseDTO;
import com.alves.cep.service.ValidationService;
import com.alves.cep.service.request.ViaCepRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CepControllerTest {

    @InjectMocks
    private CepController cepController;
    private MockMvc mockMvc;
    @Mock
    private ValidationService validationService;
    @Mock
    private ViaCepRequest viaCepRequest;

    private ViaCepResponseDTO viaCepResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cepController).build();
        viaCepResponseDTO = new ViaCepResponseDTO("teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste",null);
    }

    @Test
    void buscaCepController() throws Exception {

        when(validationService.isValidCep(anyString())).thenReturn(true);
        when(viaCepRequest.consultaCep(anyString())).thenReturn(viaCepResponseDTO);

        mockMvc.perform(post("/busca/cep/86300000"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                   System.out.println(result.getResponse().getContentAsString());
                   result.getResponse().getContentAsString().contains(viaCepResponseDTO.getCep());
                });

    }

    @Test
    void buscaCepControllerErro() throws Exception {
        viaCepResponseDTO.setErro("erro");
        when(validationService.isValidCep(anyString())).thenReturn(true);
        when(viaCepRequest.consultaCep(anyString())).thenReturn(viaCepResponseDTO);

        mockMvc.perform(post("/busca/cep/86300000"))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                    result.getResponse().getContentAsString().contains(viaCepResponseDTO.getErro());
                });

    }

    @Test
    void buscaCepControllerCepInvalido() throws Exception {
        viaCepResponseDTO.setErro("erro");
        when(validationService.isValidCep(anyString())).thenReturn(false);

        mockMvc.perform(post("/busca/cep/86300000"))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                    result.getResponse().getContentAsString().contains(viaCepResponseDTO.getErro());
                });

    }
}