package com.alves.cadastro_usuarios.controller;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.mapper.UsuarioMapper;
import com.alves.cadastro_usuarios.repository.UsuarioRepository;
import com.alves.cadastro_usuarios.service.ValidaDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class CadController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    UsuarioMapper mapper;

    @Autowired
    ValidaDados dados;


    @PostMapping
    public ResponseEntity<String> cadUsuario(@RequestBody Usuario usuario){
        List<String> erros = dados.validaDados(usuario);
        if(erros.isEmpty()){
            repository.save(mapper.domainToModel(usuario));
        } else {
            return ResponseEntity.ok(erros.toString());
        }


        return ResponseEntity.ok("usuario cadastrado");
    }
}
