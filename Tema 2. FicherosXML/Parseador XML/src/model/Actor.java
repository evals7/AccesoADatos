package model;


public class Actor {
    private String nombre;

    public Actor() {
    }

    public Actor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
