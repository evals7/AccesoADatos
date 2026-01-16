package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"perfil", "clientes"}) //importante para onetomany y manytoone
@EqualsAndHashCode(exclude= {"perfil", "clientes"}) //importante para many to many
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //establecemos la relacion con perfiles, lazy nos permite hacerlo en formato postergado
    @JoinColumn(name="id_perfil")  //la columna donde se guarda la relación
    private Perfil perfil;
    @ManyToMany(mappedBy = "trabajadores")
    private Set<Cliente> clientes = new HashSet<>(); //implementación en lista

    //constructor sin id ni comentarios o disponibilidad
    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
    }

    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion, Perfil perfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
        this.perfil = perfil;
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
