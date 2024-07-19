package com.alves.cadastro_usuarios.request;

import com.alves.cadastro_usuarios.domain.dto.ValidaCpfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ValidaCpfRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidaCpfDTO.class);

    @Autowired
    RestTemplate template;
    @Value("${api.urlValidaCpf}")
    private String URL_VALIDA_CPF;
    @Value("${api.type}")
    private String TYPE;

    public boolean validaCpf(String cpf){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);


        LOGGER.info("URL VALIDA CPF ::: " +URL_VALIDA_CPF + cpf + TYPE);

        ResponseEntity<ValidaCpfDTO> response = template.exchange(URL_VALIDA_CPF+cpf+TYPE, HttpMethod.GET, request, ValidaCpfDTO.class);

        return Objects.requireNonNull(response.getBody()).isValid();
    }
}
