package com.alves.cep.service.request;

import com.alves.cep.dto.ViaCepResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ViaCepRequestTest {

    @InjectMocks
    ViaCepRequest viaCepRequest;

    @Mock
    RestTemplate template;

    ViaCepResponseDTO viaCepResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viaCepResponseDTO =  new ViaCepResponseDTO("teste", "teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste");
    }

    @Test
    void consultaCep(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ReflectionTestUtils.setField(viaCepRequest, "URL_VIA_CEP", "localhost");
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(ViaCepResponseDTO.class))).thenReturn(ResponseEntity.ok(viaCepResponseDTO));

        assertNotNull(viaCepRequest.consultaCep("teste"));
    }
}