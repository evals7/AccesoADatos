package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    private int id;
    private String nombre;
    private Date fechaPrestamo;
    private String estado;
    private int id_libro;

    public Prestamo(String nombre, Date fechaPrestamo, String estado, int id_libro) {
        this.nombre = nombre;
        this.fechaPrestamo = fechaPrestamo;
        this.estado = estado;
        this.id_libro = id_libro;
    }

    public void mostrarDatosPres(){
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("fechaPrestamo = " + fechaPrestamo);
        System.out.println("estado = " + estado);
        System.out.println("id_libro = " + id_libro);
    }
}
