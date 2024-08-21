package com.alves.login.controller;

import com.alves.login.domain.Usuario;
import com.alves.login.domain.dto.ResponseLoginDTO;
import com.alves.login.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody Usuario usuario){

        if(service.validaLogin(usuario)){
            return ResponseEntity.badRequest().build();
        }

        ResponseLoginDTO dto = service.login(usuario);
        return ResponseEntity.ok(dto);

    }
}
