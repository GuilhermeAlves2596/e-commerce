package com.alves.cadastro_usuarios.service.utils;

import org.springframework.stereotype.Component;

@Component
public class util {

    public static boolean isNullOrBlank(String s){
        if(s == null || s.isBlank()){
            return true;
        }
        return false;
    }
}
