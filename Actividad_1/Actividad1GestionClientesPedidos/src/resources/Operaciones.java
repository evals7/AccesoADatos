package resources;

import model.Cliente;
import model.Pedido;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;

public class Operaciones {

    public void grabarObjeto(String path) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1, "Paco", "paco@gmail.com"));
        listaClientes.add(new Cliente(2, "Jesús", "jesus@gmail.com"));
        listaClientes.add(new Cliente(3, "Carmen", "carmen@gmail.com"));
        listaClientes.add(new Cliente(4, "Lola", "lola@gmail.com"));

        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(1, listaClientes.get(1), "cebolla", "5"));
        listaPedidos.add(new Pedido(1, listaClientes.get(2), "puerro", "2"));
        listaPedidos.add(new Pedido(1, listaClientes.get(3), "melón", "10"));
        listaPedidos.add(new Pedido(1, listaClientes.get(4), "manzana", "3"));
        listaPedidos.add(new Pedido(1, listaClientes.get(1), "piña", "4"));

        File file = new File(path);
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(fileWriter = new FileWriter(file, true));
            printWriter.println("id, nombre, email");
            for (Cliente cliente : listaClientes) {
                printWriter.println(cliente);
            }
            printWriter.println();
            printWriter.println("id, clienteId, producto, cantidad");
            for (Pedido pedido : listaPedidos) {
                printWriter.println(pedido);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            printWriter.close();
        }


    }
}
