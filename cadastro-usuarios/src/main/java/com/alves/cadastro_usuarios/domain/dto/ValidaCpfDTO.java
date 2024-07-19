package com.alves.cadastro_usuarios.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidaCpfDTO {

    private boolean valid;
    private String formatted;

}
