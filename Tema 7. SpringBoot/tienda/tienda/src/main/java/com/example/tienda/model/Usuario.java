package com.example.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

//TODO cuidado con el hash y el toString, que pueden causar problemas
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private long id; //lo marcamos como long para evitar problemas

    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String pass;


    //constructor sin el ID porque no lo vamos a utilizar
    public Usuario(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }
}
