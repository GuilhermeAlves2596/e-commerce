package com.alves.cadastro_usuarios.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void enderecoTest(){
        Endereco endereco = new Endereco("teste", "teste","teste","teste","teste","teste");
        Endereco endereco1 = new Endereco();

        endereco1.setBairro("teste");

        assertEquals("teste", endereco1.getBairro());

        assertEquals("teste", endereco.getRua());
        assertEquals("teste", endereco.getBairro());
        assertEquals("teste", endereco.getCidade());
        assertEquals("teste", endereco.getEstado());
        assertEquals("teste", endereco.getPais());
        assertEquals("teste", endereco.getCep());
    }
}