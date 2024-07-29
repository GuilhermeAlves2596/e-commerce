package com.alves.cadastro_usuarios.model;

import com.alves.cadastro_usuarios.domain.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioModelTest {


    @Test
    void usuarioModelTest(){
        UsuarioModel usuarioModel = new UsuarioModel();
        Endereco endereco = new Endereco("teste", "teste","teste","teste","teste","teste");
        usuarioModel.setNome("teste");

        UsuarioModel usuarioModel1 = new UsuarioModel(1L, "teste","teste","teste","teste","teste",endereco, "teste",true);

        assertEquals("teste", usuarioModel.getNome());
        assertEquals("teste", usuarioModel1.getSobreNome());
        assertEquals("teste", usuarioModel1.getCpf());
        assertEquals("teste", usuarioModel1.getDataNascimento());
        assertEquals("teste", usuarioModel1.getEmail());
        assertEquals("teste", usuarioModel1.getEndereco().getBairro());
        assertEquals("teste", usuarioModel1.getNumTelefone());
        assertEquals(1L, usuarioModel1.getId());
        assertEquals(true, usuarioModel1.getIsAdm());
    }

    @Test
    void usuarioModelBuilderTest(){
        Endereco endereco = new Endereco("teste", "teste","teste","teste","teste","teste");

        UsuarioModel usuarioModel = UsuarioModel.builder()
                .id(1L)
                .nome("teste")
                .sobreNome("teste")
                .cpf("teste")
                .dataNascimento("teste")
                .email("teste")
                .endereco(endereco)
                .numTelefone("teste")
                .isAdm(true)
                .build();


        assertEquals("teste", usuarioModel.getNome());
        assertEquals("teste", usuarioModel.getSobreNome());
        assertEquals("teste", usuarioModel.getCpf());
        assertEquals("teste", usuarioModel.getDataNascimento());
        assertEquals("teste", usuarioModel.getEmail());
        assertEquals("teste", usuarioModel.getEndereco().getBairro());
        assertEquals("teste", usuarioModel.getNumTelefone());
        assertEquals(1L, usuarioModel.getId());
        assertEquals(true, usuarioModel.getIsAdm());
    }

}