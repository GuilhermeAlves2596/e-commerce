package com.alves.cadastro_usuarios.controller;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ResponseDTO;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import com.alves.cadastro_usuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static com.alves.cadastro_usuarios.utils.Constantes.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CadControllerTest {

    @InjectMocks
    private CadController cadController;
    @Mock
    private UsuarioService service;
    private MockMvc mockMvc;

    private Usuario usuario;
    private Endereco endereco;
    private ResponseDTO responseDTO;
    private UsuarioModel usuarioModel;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        responseDTO = new ResponseDTO();
        endereco =  new Endereco("teste", "teste","teste","teste","teste","teste");
        usuario = new Usuario("teste", "teste", "teste", "teste", "teste", "teste", endereco, "teste");
        usuarioModel = new UsuarioModel(1L, "teste","teste","teste","teste","teste",endereco, "teste","teste");
        this.mockMvc = MockMvcBuilders.standaloneSetup(cadController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void cadUsuario() throws Exception {
        responseDTO.setResultado(USUARIO_CADASTRADO);
        when(service.cadastrar(Mockito.<Usuario>any())).thenReturn(responseDTO);

        String content = new ObjectMapper().writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(cadController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void cadUsuarioErro() throws Exception {
        List<String> erros = new ArrayList<>();
        erros.add("erro");
        responseDTO.setErros(erros);
        when(service.cadastrar(Mockito.<Usuario>any())).thenReturn(responseDTO);

        String content = new ObjectMapper().writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(cadController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void findByCpf() throws Exception {
        responseDTO.setUsuarioModel(usuarioModel);
        when(service.findByCpf(Mockito.anyString())).thenReturn(responseDTO);

        mockMvc.perform(get("/usuario/000.000.000-00"))
                .andExpect(status().isOk());
    }

    @Test
    void findByCpfErro() throws Exception {
        List<String> erros = new ArrayList<>();
        erros.add("erro");
        responseDTO.setErros(erros);
        when(service.findByCpf(Mockito.anyString())).thenReturn(responseDTO);

        mockMvc.perform(get("/usuario/000.000.000-00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete() throws Exception {
        responseDTO.setResultado(USUARIO_DELETE);
        when(service.delete(Mockito.anyString())).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/000.000.000-00"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteErro() throws Exception {
        List<String> erros = new ArrayList<>();
        erros.add("erro");
        responseDTO.setErros(erros);
        when(service.delete(Mockito.anyString())).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/000.000.000-00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        responseDTO.setResultado(USUARIO_ATUALIZADO);
        when(service.update(Mockito.<Usuario>any())).thenReturn(responseDTO);

        String content = new ObjectMapper().writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(cadController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void updateErro() throws Exception {
        List<String> erros = new ArrayList<>();
        erros.add("erro");
        responseDTO.setErros(erros);
        when(service.update(Mockito.<Usuario>any())).thenReturn(responseDTO);

        String content = new ObjectMapper().writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(cadController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }
}