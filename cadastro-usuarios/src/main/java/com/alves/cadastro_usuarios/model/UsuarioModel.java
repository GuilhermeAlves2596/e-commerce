package com.alves.cadastro_usuarios.model;

import com.alves.cadastro_usuarios.domain.Endereco;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobreNome;
    private String cpf;
    private String dataNascimento;
    private String email;

    @Embedded
    private Endereco endereco;

    private String numTelefone;
    private String senha;

}
