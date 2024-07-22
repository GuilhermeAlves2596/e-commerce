package com.alves.cadastro_usuarios.utils;

import org.springframework.stereotype.Component;

@Component
public class Util {

    public static boolean isNullOrBlank(String s){
        if(s == null || s.isBlank()){
            return true;
        }
        return false;
    }
}
