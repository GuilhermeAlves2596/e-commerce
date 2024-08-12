package com.alves.cadastro_usuarios.mapper;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {
    @InjectMocks
    UsuarioMapper usuarioMapper;
    private Usuario usuario;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco  = new Endereco("teste", "teste","teste","teste","teste","teste");
        usuario = new Usuario("teste", "teste", "teste", "teste", "teste", "teste", endereco, "teste");
    }

    @Test
    void domainToModel() {
        UsuarioModel model = usuarioMapper.domainToModel(usuario);
        assertEquals("teste", model.getNome());
    }

    @Test
    void domainToModelUpdate() {
        UsuarioModel model = usuarioMapper.domainToModelUpdate(usuario, 1L);
        assertEquals("teste", model.getNome());
    }
}