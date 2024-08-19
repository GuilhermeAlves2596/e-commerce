package com.alves.login.controller;

import com.alves.login.domain.Usuario;
import com.alves.login.domain.dto.ResponseLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<ResponseLoginDTO> login(Usuario usuario){


        return ResponseEntity.ok(new ResponseLoginDTO());
    }
}
