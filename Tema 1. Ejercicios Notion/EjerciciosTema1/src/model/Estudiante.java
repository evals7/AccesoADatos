package model;

public class Estudiante {
    private String nombre;
    private int edad, calificacion;

    public Estudiante() {
    }

    public Estudiante(String nombre, int edad, int calificacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", calificacion=" + calificacion +
                '}';
    }
}
