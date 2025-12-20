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
@NamedQuery(name = "Trabajador.getByEmail", query = "FROM Trabajador t WHERE t.correo=:mail")  //query del trabajadorDAO para seleccionarTrabajador() que insertaremos allí con su nombre
@NamedQuery(name = "Trabajador.getAll", query = "FROM Trabajador")
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
    @Transient                  //La etiqueta transient hace que estos atributos no se persistan en la base de datos
    private String comentarios;
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

    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("antiguedad = " + antiguedad);
        System.out.println("correo = " + correo);
        System.out.println("salario = " + salario);
        System.out.println("direccion = " + direccion);
        System.out.println("comentarios = " + comentarios);
        System.out.println("disponibilidad = " + disponibilidad);
    }

}
