package com.alves.cadastro_usuarios.controller;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ResponseDTO;
import com.alves.cadastro_usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class CadController {

    @Autowired
    UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<ResponseDTO> cadUsuario(@RequestBody Usuario usuario){
        ResponseDTO responseDTO = usuarioService.cadastrar(usuario);
        if(responseDTO.erros == null){
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> findByCpf(@PathVariable String cpf){
        ResponseDTO responseDTO = usuarioService.findByCpf(cpf);
        if(responseDTO.erros == null){
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String cpf){
        ResponseDTO responseDTO = usuarioService.delete(cpf);
        if(responseDTO.erros == null){
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody Usuario usuario){
        ResponseDTO responseDTO = usuarioService.update(usuario);
        if(responseDTO.getErros() == null){
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().body(responseDTO);
    }
}
