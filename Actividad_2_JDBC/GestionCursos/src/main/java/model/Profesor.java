package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    private int id;
    private String nombre;
    private List<Curso> listaCursos;

    public Profesor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
