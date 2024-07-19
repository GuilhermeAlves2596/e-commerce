package com.alves.cep.service.request;

import com.alves.cep.dto.ViaCepResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ViaCepRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViaCepRequest.class);

    @Autowired
    private RestTemplate template;
    @Value("${apiCep}")
    private String URL_VIA_CEP;

    public ViaCepResponseDTO consultaCep(String cep) {

        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        LOGGER.info(String.format("URL VIA CEP: " + URL_VIA_CEP, cep));

        ResponseEntity<ViaCepResponseDTO> response = template.exchange(String.format(URL_VIA_CEP, cep), HttpMethod.GET, request, ViaCepResponseDTO.class);

        return response.getBody();
    }

//    public ViaCepResponseDTO consultaCep(String cep) throws IOException, InterruptedException {
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//
//        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(String.format(URL_VIA_CEP, cep)))
//                .header("Content-Type", "application/json")
//                .build();
//
//        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        return objectMapper.readValue(response.body(), ViaCepResponseDTO.class);
//    }
}
