package com.alves.cadastro_usuarios.request;

import com.alves.cadastro_usuarios.domain.dto.ValidaCpfDTO;
import com.alves.cadastro_usuarios.domain.dto.ViaCepResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RequestsTest {

    @InjectMocks
    Requests requests;
    @Mock
    RestTemplate template;
    ViaCepResponseDTO viaCepResponseDTO;
    ValidaCpfDTO cpfDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viaCepResponseDTO =  new ViaCepResponseDTO("teste", "teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste");
        cpfDTO = new ValidaCpfDTO(true, "teste");
    }

    @Test
    void validaCpfTest(){
        ReflectionTestUtils.setField(requests, "URL_VALIDA_CPF", "localhost");
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(ValidaCpfDTO.class))).thenReturn(ResponseEntity.ok(cpfDTO));

        assertTrue(requests.validaCpf("123456789"));
    }

    @Test
    void validaCepTest(){
        ReflectionTestUtils.setField(requests, "URL_VALIDA_CEP", "localhost");
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(ViaCepResponseDTO.class))).thenReturn(ResponseEntity.ok(viaCepResponseDTO));

        assertNotNull(requests.validaCep("123465"));
    }
}