package com.alves.cadastro_usuarios.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    Endereco endereco;

    @BeforeEach
    void setUp() {
       endereco  = new Endereco("teste", "teste","teste","teste","teste","teste");
    }

    @Test
    void usuarioTest(){
        Usuario usuario = new Usuario("teste", "teste", "teste", "teste", "teste", "teste", endereco, true);
        usuario.setNome("teste123");

        assertEquals("teste123", usuario.getNome());
        assertEquals("teste", usuario.getSobreNome());
        assertEquals("teste", usuario.getCpf());
        assertEquals("teste", usuario.getDataNascimento());
        assertEquals("teste", usuario.getEmail());
        assertEquals("teste", usuario.getNumTelefone());
        assertEquals("teste", usuario.getEndereco().getBairro());
        assertTrue(usuario.getIsAdmin());

    }

}