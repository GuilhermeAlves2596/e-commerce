package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.request.ValidaCpfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.alves.cadastro_usuarios.service.utils.util.isNullOrBlank;

@Service
public class ValidaDados {

    List<String> erro = new ArrayList<>();
    @Autowired
    ValidaCpfRequest cpfRequest;

    public List<String> validaDados(Usuario usuario){
        validaCpf(usuario.getCpf());



        return erro;
    }

    public void validaCpf(String cpf){
        if(isNullOrBlank(cpf)){
            erro.add("CPF não pode ser vazio");
        }

        if(!cpfRequest.validaCpf(cpf)){
            erro.add("O CPF informado é inválido");
        }

    }
}
