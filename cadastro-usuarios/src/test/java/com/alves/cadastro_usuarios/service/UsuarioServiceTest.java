package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ResponseDTO;
import com.alves.cadastro_usuarios.mapper.UsuarioMapper;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import com.alves.cadastro_usuarios.repository.UsuarioRepository;
import com.alves.cadastro_usuarios.service.Bcrypt.BcryptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.alves.cadastro_usuarios.utils.Constantes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;
    @Mock
    UsuarioRepository repository;
    @Mock
    ValidaDados validaDados;
    @Mock
    UsuarioMapper mapper;
    @Mock
    BcryptService bcryptService;

    Usuario usuario;
    Endereco endereco;

    UsuarioModel usuarioModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco  = new Endereco("teste", "teste","teste","teste","teste","teste");
        usuario = new Usuario("teste", "teste", "teste", "teste", "teste", "teste", endereco, "teste");
        usuarioModel = new UsuarioModel(1L,"teste", "teste", "teste", "teste", "teste", endereco, "teste", "teste");
    }

    @Test
    void cadastrarTest(){
        List<String> erros = new ArrayList<>();
        when(validaDados.validaDados(usuario)).thenReturn(erros);
        when(bcryptService.encoder(usuario.getSenha())).thenReturn("senhaHash");
        when(mapper.domainToModel(usuario)).thenReturn(usuarioModel);
        ResponseDTO expected = usuarioService.cadastrar(usuario);

        assertEquals(USUARIO_CADASTRADO, expected.getResultado());
    }

    @Test
    void cadastrarJaExistenteTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(usuarioModel);

        ResponseDTO expected = usuarioService.cadastrar(usuario);

        assertEquals(USUARIO_EXISTENTE, expected.getErros().get(0));
    }

    @Test
    void cadastrarErroTest(){
        List<String> erro = new ArrayList<>();
        erro.add("erro");
        when(validaDados.validaDados(usuario)).thenReturn(erro);
        ResponseDTO expected = usuarioService.cadastrar(usuario);

        assertEquals("erro", expected.getErros().get(0));
    }

    @Test
    void findByCpfTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(usuarioModel);
        ResponseDTO expected = usuarioService.findByCpf(usuario.getCpf());

        assertEquals("teste", expected.getUsuarioModel().getCpf());
    }

    @Test
    void findByCpfNullTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(null);
        ResponseDTO expected = usuarioService.findByCpf(usuario.getCpf());

        assertEquals(USUARIO_NAO_ENCONTRADO, expected.getErros().get(0));
    }

    @Test
    void deleteTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(usuarioModel);

        ResponseDTO expected = usuarioService.delete(usuario.getCpf());
        assertEquals(USUARIO_DELETE, expected.getResultado());
    }

    @Test
    void deleteErroTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(null);

        ResponseDTO expected = usuarioService.delete(usuario.getCpf());
        assertEquals(USUARIO_NAO_ENCONTRADO, expected.getErros().get(0));
    }

    @Test
    void updateTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(usuarioModel);
        when(validaDados.validaDados(usuario)).thenReturn(new ArrayList<>());
        when(bcryptService.encoder(usuario.getSenha())).thenReturn("senhaHash");
        when(mapper.domainToModelUpdate(usuario, usuarioModel.getId())).thenReturn(usuarioModel);

        ResponseDTO expected = usuarioService.update(usuario);

        assertEquals(USUARIO_ATUALIZADO, expected.getResultado());

    }

    @Test
    void updateNaoEncontradoTest(){
        when(repository.findByCpf(usuario.getCpf())).thenReturn(null);

        ResponseDTO expected = usuarioService.update(usuario);

        assertEquals(USUARIO_NAO_ENCONTRADO, expected.getErros().get(0));

    }

    @Test
    void updateErroTest(){
        List<String> erro = new ArrayList<>();
        erro.add("erro");
        when(repository.findByCpf(usuario.getCpf())).thenReturn(usuarioModel);
        when(validaDados.validaDados(usuario)).thenReturn(erro);
        when(mapper.domainToModelUpdate(usuario, usuarioModel.getId())).thenReturn(usuarioModel);

        ResponseDTO expected = usuarioService.update(usuario);

        assertEquals("erro", expected.getErros().get(0));

    }
}