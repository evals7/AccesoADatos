package com.example.tienda.repository;

import com.example.tienda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Interfaces con la firma del metodo, publico y abstracto
//para añadir los metodos CRUD podemos extender de JPARepository y le damos el tipo de objeto y el tipo de PK
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



}
