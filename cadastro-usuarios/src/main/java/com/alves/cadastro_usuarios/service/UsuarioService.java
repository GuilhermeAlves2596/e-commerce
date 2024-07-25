package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ResponseDTO;
import com.alves.cadastro_usuarios.mapper.UsuarioMapper;
import com.alves.cadastro_usuarios.model.UsuarioModel;
import com.alves.cadastro_usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alves.cadastro_usuarios.utils.Constantes.*;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioMapper mapper;
    @Autowired
    ValidaDados dados;

    public ResponseDTO cadastrar(Usuario usuario){
        UsuarioModel usuarioModel = repository.findByCpf(usuario.getCpf());
        if(usuarioModel == null){
            List<String> erros = dados.validaDados(usuario);

            if(erros.isEmpty()){
                repository.save(mapper.domainToModel(usuario));
                return new ResponseDTO(USUARIO_CADASTRADO, null);
            } else {
                return new ResponseDTO(null, erros.toString());
            }
        } else {
            return new ResponseDTO(null, USUARIO_EXISTENTE);
        }
    }

    public ResponseDTO findByCpf(String cpf){
        UsuarioModel usuarioModel = repository.findByCpf(cpf);
        if(usuarioModel == null){
            return new ResponseDTO(null, USUARIO_NAO_ENCONTRADO);
        }

        return new ResponseDTO(usuarioModel);
    }

    public ResponseDTO delete(String cpf){
        UsuarioModel usuarioModel = repository.findByCpf(cpf);
        if(usuarioModel == null){
            return new ResponseDTO(null, USUARIO_NAO_ENCONTRADO);
        }

        repository.delete(usuarioModel);
        return new ResponseDTO(USUARIO_DELETE, null);
    }

    public ResponseDTO update(Usuario usuario){
        UsuarioModel usuarioModel = repository.findByCpf(usuario.getCpf());
        if(usuarioModel == null){
            return new ResponseDTO(null, USUARIO_NAO_ENCONTRADO);
        }

        List<String> erros = dados.validaDados(usuario);
        if(erros.isEmpty()){
            repository.save(mapper.domainToModelUpdate(usuario, usuarioModel.getId()));
            return new ResponseDTO(USUARIO_ATUALIZADO, null);
        } else {
            return new ResponseDTO(USUARIO_NAO_ATUALIZADO, erros.toString());
        }
    }
}
