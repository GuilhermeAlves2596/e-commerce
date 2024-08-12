package com.alves.cadastro_usuarios.service.Bcrypt;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptService {


    public String encoder(String senha){
        return BCrypt.withDefaults().hashToString(12, senha.toCharArray());
    }

    public boolean matches(String senha, String senhaHash){
        BCrypt.Result result = BCrypt.verifyer().verify(senha.toCharArray(), senhaHash);

        return result.verified;
    }

}
