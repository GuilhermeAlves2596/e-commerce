package com.alves.cadastro_usuarios.domain.dto;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> erros = new ArrayList<>();
        erros.add("teste");
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultado("teste");
        responseDTO.setErros(erros);
        responseDTO.setUsuarioModel(usuarioModel);

        assertEquals("teste", responseDTO.getResultado());
        assertEquals("teste", responseDTO.getErros().get(0));
        assertEquals("teste", responseDTO.getUsuarioModel().getCpf());
    }

    @Test
    void responseDTOConstructorTest(){
        List<String> erros = new ArrayList<>();
        erros.add("teste");
        ResponseDTO responseDTO = new ResponseDTO("teste", erros);

        assertEquals("teste", responseDTO.getResultado());
        assertEquals("teste", responseDTO.getErros().get(0));

    }

    @Test
    void responseDTOConstructorModelTest(){
        ResponseDTO responseDTO = new ResponseDTO(usuarioModel);

        assertEquals("teste", responseDTO.getUsuarioModel().getNome());


    }
}