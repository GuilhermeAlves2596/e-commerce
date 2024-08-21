package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ResponseDTO;
import com.alves.cadastro_usuarios.mapper.UsuarioMapper;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import com.alves.cadastro_usuarios.repository.UsuarioRepository;
import com.alves.cadastro_usuarios.service.Bcrypt.BcryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.alves.cadastro_usuarios.utils.Constantes.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioMapper mapper;
    @Autowired
    ValidaDados dados;
    @Autowired
    BcryptService bcryptService;

    public ResponseDTO cadastrar(Usuario usuario){
        List<String> erros = new ArrayList<>();
        UsuarioModel usuarioModel = repository.findByCpf(usuario.getCpf());
        if(usuarioModel == null){
            erros = dados.validaDados(usuario);

            if(erros.isEmpty()){
                usuario.setSenha(bcryptService.encoder(usuario.getSenha()));
                repository.save(mapper.domainToModel(usuario));
                return new ResponseDTO(USUARIO_CADASTRADO, null);
            } else {
                return new ResponseDTO(null, erros);
            }
        } else {
            erros.add(USUARIO_EXISTENTE);
            return new ResponseDTO(null, erros);
        }
    }

    public ResponseDTO findByCpf(String cpf){
        List<String> erros = new ArrayList<>();
        UsuarioModel usuarioModel = repository.findByCpf(cpf);
        if(usuarioModel == null){
            erros.add(USUARIO_NAO_ENCONTRADO);
            return new ResponseDTO(null, erros);
        }

        return new ResponseDTO(usuarioModel);
    }

    public ResponseDTO delete(String cpf){
        List<String> erros = new ArrayList<>();
        UsuarioModel usuarioModel = repository.findByCpf(cpf);
        if(usuarioModel == null){
            erros.add(USUARIO_NAO_ENCONTRADO);
            return new ResponseDTO(null, erros);
        }

        repository.delete(usuarioModel);
        return new ResponseDTO(USUARIO_DELETE, null);
    }

    public ResponseDTO update(Usuario usuario){
        List<String> erros = new ArrayList<>();
        UsuarioModel usuarioModel = repository.findByCpf(usuario.getCpf());
        if(usuarioModel == null){
            erros.add(USUARIO_NAO_ENCONTRADO);
            return new ResponseDTO(null, erros);
        }

        erros = dados.validaDados(usuario);
        if(erros.isEmpty()){
            usuario.setSenha(bcryptService.encoder(usuario.getSenha()));
            repository.save(mapper.domainToModelUpdate(usuario, usuarioModel.getId()));
            return new ResponseDTO(USUARIO_ATUALIZADO, null);
        } else {
            return new ResponseDTO(USUARIO_NAO_ATUALIZADO, erros);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = repository.findByCpf(username);
        if(usuarioModel == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(usuarioModel.getCpf(), usuarioModel.getSenha(), new ArrayList<>());
    }
}
