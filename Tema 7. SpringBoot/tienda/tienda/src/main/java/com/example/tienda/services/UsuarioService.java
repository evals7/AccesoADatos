package com.example.tienda.services;

import com.example.tienda.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service        //la clase sea tratada como servicio
@Transactional  //activamos el autocomit
public class UsuarioService {

    //métodos con lógica que llaman al repositorio, tenemos que crear el repositorio
    @Autowired //si no lo inicializamos, este atributo lo hace por defecto, es una autoinicialización ( bootstrap)
    private UsuarioRepository repository;


    public void getAll(){
        //lógica para obtener todos los elementos
        repository.findAll();
    }
}
