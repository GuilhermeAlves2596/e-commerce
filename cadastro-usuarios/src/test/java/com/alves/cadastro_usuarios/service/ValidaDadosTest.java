package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ViaCepResponseDTO;
import com.alves.cadastro_usuarios.request.Requests;
import com.alves.cadastro_usuarios.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ValidaDadosTest {

    @InjectMocks
    ValidaDados validaDados;
    @Mock
    Util util;
    @Mock
    Requests requests;

    Usuario usuario;
    Endereco endereco;
    ViaCepResponseDTO viaCepResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco  = new Endereco("teste", "teste","teste","teste","teste","88888-000");
        usuario = new Usuario("teste", "teste", "123.444.555-00", "2010-05-05", "teste@teste.com", "99999-1111", endereco, "teste");
        viaCepResponseDTO = new ViaCepResponseDTO("teste","teste","teste","teste","teste","teste","teste","teste","teste","teste","teste",null);
    }

    @Test
    void validaDadosTest(){
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(0, errors.size());
    }

    @Test
    void validaDadosErroCpfTest(){
        when(requests.validaCpf(usuario.getCpf())).thenReturn(false);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosErroCpfVazioTest(){
        usuario.setCpf("");
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosNomeSobreNomeTest(){
        usuario.setNome("");
        usuario.setSobreNome("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(2, errors.size());
    }

    @Test
    void validaDadosDtNascimentoVazioTest(){
        usuario.setDataNascimento("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosDtNascimentoFuturoTest(){
        usuario.setDataNascimento("2025-05-15");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosEmailVazioTest(){
        usuario.setEmail("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosEmailInvalidoTest(){
        usuario.setEmail("teste");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosFoneVazioTest(){
        usuario.setNumTelefone("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosFoneInvalidoTest(){
        usuario.setNumTelefone("123");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosEnderecoTest(){
        endereco.setRua("");
        endereco.setBairro("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setPais("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(5, errors.size());
    }

    @Test
    void validaDadosCepVazioTest(){
        endereco.setCep("");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosCepTamanhoInvalidoTest(){
        endereco.setCep("456");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosCepInvalidoTest(){
        viaCepResponseDTO.setErro("erro");
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }

    @Test
    void validaDadosAdminTest(){
        usuario.setSenha(null);
        when(requests.validaCpf(usuario.getCpf())).thenReturn(true);
        when(requests.validaCep(anyString())).thenReturn(viaCepResponseDTO);

        List<String> errors = validaDados.validaDados(usuario);

        assertEquals(1, errors.size());
    }
}