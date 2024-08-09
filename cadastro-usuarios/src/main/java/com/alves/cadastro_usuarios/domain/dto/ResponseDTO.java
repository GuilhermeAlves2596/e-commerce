package com.alves.cadastro_usuarios.domain.dto;

import com.alves.cadastro_usuarios.model.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    public String resultado;
    public List<String> erros;
    @JsonProperty("usuario")
    public UsuarioModel usuarioModel;


    public ResponseDTO(String resultado, List<String> erros) {
        this.resultado = resultado;
        this.erros = erros;
    }

    public ResponseDTO(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
