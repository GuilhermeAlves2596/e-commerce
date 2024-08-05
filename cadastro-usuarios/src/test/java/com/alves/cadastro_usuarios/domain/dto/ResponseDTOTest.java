package com.alves.cadastro_usuarios.domain.dto;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDTOTest {

    UsuarioModel usuarioModel;

    Endereco endereco;

    @BeforeEach
    void setUp(){
        endereco = new Endereco("teste","teste","teste","teste","teste","teste");
        usuarioModel = new UsuarioModel(1L,"teste","teste","teste","teste","teste", endereco, "teste", "teste");
    }

    @Test
    void responseDTOTest(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultado("teste");
        responseDTO.setErros("teste");
        responseDTO.setUsuarioModel(usuarioModel);

        assertEquals("teste", responseDTO.getResultado());
        assertEquals("teste", responseDTO.getErros());
        assertEquals("teste", responseDTO.getUsuarioModel().getCpf());
    }

    @Test
    void responseDTOConstructorTest(){
        ResponseDTO responseDTO = new ResponseDTO("teste", "teste");

        assertEquals("teste", responseDTO.getResultado());
        assertEquals("teste", responseDTO.getErros());

    }

    @Test
    void responseDTOConstructorModelTest(){
        ResponseDTO responseDTO = new ResponseDTO(usuarioModel);

        assertEquals("teste", responseDTO.getUsuarioModel().getNome());


    }
}