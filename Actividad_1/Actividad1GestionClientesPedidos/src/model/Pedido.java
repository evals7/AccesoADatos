package model;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int id, clienteId;
    private String producto, cantidad;

    public Pedido() {
    }

    //para clienteId, pasamos el Cliente para obtener la información del cliente, concretamente el id
    public Pedido(int id, Cliente cliente, String producto, String cantidad) {
        this.id = id;
        this.clienteId = cliente.getId();
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s, %s", id, clienteId, producto, cantidad);
    }
}
