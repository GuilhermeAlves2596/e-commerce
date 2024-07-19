package com.alves.cep.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean isValidCep(String cep){
        if(cep == null || cep.isBlank()){
            return false;
        }

        cep = cep.replaceAll("[^0-9 ]", "");

        if(cep.length() != 8){
            return false;
        }

        return true;
    }
}
