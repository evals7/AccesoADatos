package model;

import java.util.Arrays;

public class Pelicula {

    private String titulo, director;
    private Actor[] actores;
    private String genero;
    private int anio;
    private double calificacion;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, Actor[] actores, String genero, int anio, double calificacion) {
        this.titulo = titulo;
        this.director = director;
        this.actores = actores;
        this.genero = genero;
        this.anio = anio;
        this.calificacion = calificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Actor[] getActores() {
        return actores;
    }

    public void setActores(Actor[] actores) {
        this.actores = actores;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", actores=" + Arrays.toString(actores) +
                ", genero='" + genero + '\'' +
                ", anio=" + anio +
                ", calificacion=" + calificacion +
                '}';
    }
}
