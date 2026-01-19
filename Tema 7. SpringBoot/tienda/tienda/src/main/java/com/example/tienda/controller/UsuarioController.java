package com.example.tienda.controller;

import com.example.tienda.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController //controlador de tipo Rest, admite peticiones entrantes a través de HTTP
public class UsuarioController {

    //aquí se declaran los endpoints que admitiran info y se la darán a los services.
    //los services admitiran la info, y ejecutarán cosas contra la bbdd; le pasan la pelota al repositorio
    //el repositorio irá contra la tabla x de la bbdd

    @Autowired
    private UsuarioService usuarioService;
}
