package com.alves.cadastro_usuarios.mapper;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioModel domainToModel(Usuario usuario){
        return UsuarioModel.builder()
                .nome(usuario.getNome())
                .sobreNome(usuario.getSobreNome())
                .cpf(usuario.getCpf())
                .dataNascimento(usuario.getDataNascimento())
                .email(usuario.getEmail())
                .endereco(usuario.getEndereco())
                .numTelefone(usuario.getNumTelefone())
                .isAdm(usuario.getIsAdmin())
                .build();
    }

    public UsuarioModel domainToModelUpdate(Usuario usuario, Long id){
        return UsuarioModel.builder()
                .id(id)
                .nome(usuario.getNome())
                .sobreNome(usuario.getSobreNome())
                .cpf(usuario.getCpf())
                .dataNascimento(usuario.getDataNascimento())
                .email(usuario.getEmail())
                .endereco(usuario.getEndereco())
                .numTelefone(usuario.getNumTelefone())
                .isAdm(usuario.getIsAdmin())
                .build();
    }
}
