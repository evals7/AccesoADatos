package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "empleados")
public class Trabajador implements Serializable { //lo implementamos por defecto por si acaso lo necesitamos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int antiguedad;
    @Column (name = "mail", unique = true)
    private String correo;
    @Column
    private int salario;
    @Embedded
    private Direccion direccion;
    @Transient
    private String comentarios; //podemos crear atributos que no estén en la bbdd
    @Transient
    private boolean disponibilidad;


    //constructor sin id ni comentarios o disponibilidad
    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
    }

}
