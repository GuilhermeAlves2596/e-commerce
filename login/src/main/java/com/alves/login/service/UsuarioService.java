package com.alves.login.service;

import com.alves.login.domain.Usuario;
import com.alves.login.domain.dto.ResponseLoginDTO;
import com.alves.login.model.UsuarioModel;
import com.alves.login.repository.UsuarioRepository;
import com.alves.login.service.Bcrypt.BcryptService;
import com.alves.login.service.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alves.login.utils.Constantes.*;
import static com.alves.login.utils.Util.isNullOrBlank;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    BcryptService bCrypt;
    @Autowired
    JwtTokenProvider tokenProvider;

    public ResponseLoginDTO login(Usuario usuario){
        UsuarioModel usuarioModel = buscaUsuario(usuario);

        if(usuarioModel == null){
            return new ResponseLoginDTO(USUARIO_INVALIDO);
        }

        if(bCrypt.matches(usuario.getSenha(), usuarioModel.getSenha())){
            String token = tokenProvider.generateToken(usuario.getLogin(), usuarioModel.getId());

            return new ResponseLoginDTO(token);
        } else {
            return new ResponseLoginDTO(ERRO_LOGIN);
        }

    }

    public UsuarioModel buscaUsuario(Usuario usuario){
        if(isEmailOrCpf(usuario.getLogin()).equals(EMAIL)){
           return repository.findByEmail(usuario.getLogin());
        } else {
           return repository.findByCpf(usuario.getLogin());
        }
    }


    public String isEmailOrCpf(String login){
        if(login.contains("@")){
            return EMAIL;
        } else {
            return CPF;
        }
    }

    public boolean validaLogin(Usuario usuario){
        return isNullOrBlank(usuario.getLogin()) || isNullOrBlank(usuario.getSenha());
    }

}
