package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private int id;
    private String isbn, titulo, autor, editorial;
    private int anoPublicacion;
    private String categoria;
    private double precio;
    private int stock;

    //constructor sin ID
    public Libro(String isbn, String titulo, String autor, String editorial, int anoPublicacion, String categoria, double precio, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anoPublicacion = anoPublicacion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public void mostrarDatosLibro(){
        System.out.println("id = " + id);
        System.out.println("isbn = " + isbn);
        System.out.println("titulo = " + titulo);
        System.out.println("autor = " + autor);
        System.out.println("editorial = " + editorial);
        System.out.println("anoPublicacion = " + anoPublicacion);
        System.out.println("categoria = " + categoria);
        System.out.println("precio = " + precio);
        System.out.println("stock = " + stock);
    }
}
