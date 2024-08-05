package com.alves.cadastro_usuarios.service;

import com.alves.cadastro_usuarios.domain.Endereco;
import com.alves.cadastro_usuarios.domain.Usuario;
import com.alves.cadastro_usuarios.domain.dto.ViaCepResponseDTO;
import com.alves.cadastro_usuarios.request.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.alves.cadastro_usuarios.utils.Constantes.*;
import static com.alves.cadastro_usuarios.utils.Util.isNullOrBlank;

@Service
public class ValidaDados {

    @Autowired
    Requests requests;

    public List<String> validaDados(Usuario usuario){
        List<String> erro = new ArrayList<>();

        validaCpf(usuario.getCpf(), erro);
        validaNome(usuario.getNome(), erro);
        validaSobreNome(usuario.getSobreNome(), erro);
        validaDtNascimento(usuario.getDataNascimento(), erro);
        validaEmail(usuario.getEmail(), erro);
        validaTelefone(usuario.getNumTelefone(), erro);
        validaEndereco(usuario.getEndereco(), erro);
        validaCep(usuario.getEndereco().getCep(), erro);
        validaIsAdmin(usuario.getSenha(), erro);

        return erro;
    }

    public void validaCpf(String cpf, List<String> erro){
        if(isNullOrBlank(cpf)){
            erro.add(CPF_VAZIO);
        } else {
            if(!requests.validaCpf(cpf)){
                erro.add(CPF_INVALIDO);
            }
        }

    }

    public void validaNome(String nome, List<String> erro){
        if(isNullOrBlank(nome)){
            erro.add(NOME_VAZIO);
        }
    }

    public void validaSobreNome(String sobreNome, List<String> erro){
        if(isNullOrBlank(sobreNome)){
            erro.add(SOBRENOME_VAZIO);
        }
    }

    public void validaDtNascimento(String dtNascimento, List<String> erro){
        if(isNullOrBlank(dtNascimento)){
            erro.add(DT_NASC_VAZIO);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dtNascimento, formatter);
            if(date.isAfter(LocalDate.now())){
                erro.add(DT_NASC_INVALIDA);
            }
        }
    }

    public void validaEmail(String email, List<String> erro){
        if(isNullOrBlank(email)){
            erro.add(EMAIL_VAZIO);
        } else {
            if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|com\\.br)$")){
                erro.add(EMAIL_INVALIDO);
            }
        }
    }

    public void validaTelefone(String fone, List<String> erro){
        if(isNullOrBlank(fone)){
            erro.add(FONE_VAZIO);
        } else {
            fone = fone.replaceAll("[^0-9]", "");
            if(!fone.matches("^.{8,9}$")){
                erro.add(FONE_INVALIDO);
            }
        }
    }

    public void validaEndereco(Endereco endereco, List<String> erro){
        if(isNullOrBlank(endereco.getRua())){
            erro.add(RUA_VAZIA);
        }

        if(isNullOrBlank(endereco.getBairro())){
            erro.add(BAIRRO_VAZIO);
        }

        if(isNullOrBlank(endereco.getCidade())){
            erro.add(CIDADE_VAZIO);
        }

        if(isNullOrBlank(endereco.getEstado())){
            erro.add(ESTADO_VAZIO);
        }

        if(isNullOrBlank(endereco.getPais())){
            erro.add(PAIS_VAZIO);
        }
    }

    public void validaCep(String cep, List<String> erro){
        if(isNullOrBlank(cep)){
            erro.add(CEP_VAZIO);
        } else {
            cep = cep.replaceAll("[^0-9]", "");
            if(cep.length() != 8){
                erro.add(CEP_INVALIDO);
            } else {
                ViaCepResponseDTO validaCep = requests.validaCep(cep);
                if(validaCep.getErro() != null){
                    erro.add(CEP_INVALIDO);
                }
            }
        }
    }

    public void validaIsAdmin(String senha, List<String> erro){
        if(senha == null){
            erro.add(SENHA_VAZIO);
        }
    }
}
