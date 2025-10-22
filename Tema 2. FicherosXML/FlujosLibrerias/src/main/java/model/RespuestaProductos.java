package model;
//CLASE PARA RECIBIR LOS PRODUCTOS QUE LEA DEL JSON

import lombok.Data;

import java.util.List;
@Data
public class RespuestaProductos { //
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;


}
