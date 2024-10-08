package com.alves.cadastro_usuarios.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String nome;
    private String sobreNome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String numTelefone;
    private Endereco endereco;
    private String senha;

}
