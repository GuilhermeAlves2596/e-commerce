package com.alves.cadastro_usuarios.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
}
