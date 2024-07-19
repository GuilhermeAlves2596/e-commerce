package com.alves.cep.controller;

import com.alves.cep.dto.ResponseDTO;
import com.alves.cep.dto.ViaCepResponseDTO;
import com.alves.cep.service.ValidationService;
import com.alves.cep.service.request.ViaCepRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/busca")
public class CepController {

    @Autowired
    ValidationService service;

    @Autowired
    ViaCepRequest request;

    @PostMapping("/cep/{cep}")
    public ResponseEntity<ResponseDTO> buscaCep(@PathVariable String cep) throws IOException, InterruptedException {

        if(service.isValidCep(cep)){
            ViaCepResponseDTO dto = request.consultaCep(cep);
            if (dto.getErro() == null){
                return ResponseEntity.ok(new ResponseDTO(dto));
            }
        }

        return ResponseEntity.badRequest().body(new ResponseDTO("CEP INVALIDO"));
    }
}
