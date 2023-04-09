package com.example.tp.controladores;

import com.example.tp.modelos.Usuario;

public interface IusuarioBD {

    Usuario elemento(String username);
    void crear(Usuario usuario);
    boolean validacion(String username,String password);


}
