package com.alves.cep.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    public ViaCepResponseDTO cep;
    public String erro;

    public ResponseDTO(ViaCepResponseDTO cep) {
        this.cep = cep;
    }

    public ResponseDTO(String erro) {
        this.erro = erro;
    }
}
