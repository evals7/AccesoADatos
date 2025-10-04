package resources;

import model.Cliente;
import model.Pedido;

import java.io.*;
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
        listaPedidos.add(new Pedido(1, listaClientes.get(0), "cebolla", "5"));
        listaPedidos.add(new Pedido(1, listaClientes.get(1), "puerro", "2"));
        listaPedidos.add(new Pedido(1, listaClientes.get(2), "melón", "10"));
        listaPedidos.add(new Pedido(1, listaClientes.get(3), "manzana", "3"));
        listaPedidos.add(new Pedido(1, listaClientes.get(0), "piña", "4"));

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

    public void escribirObjeto(String path){

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1, "Paco", "paco@gmail.com"));
        listaClientes.add(new Cliente(2, "Jesús", "jesus@gmail.com"));
        listaClientes.add(new Cliente(3, "Carmen", "carmen@gmail.com"));
        listaClientes.add(new Cliente(4, "Lola", "lola@gmail.com"));

        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(1, listaClientes.get(0), "cebolla", "5"));
        listaPedidos.add(new Pedido(1, listaClientes.get(1), "puerro", "2"));
        listaPedidos.add(new Pedido(1, listaClientes.get(2), "melón", "10"));
        listaPedidos.add(new Pedido(1, listaClientes.get(3), "manzana", "3"));
        listaPedidos.add(new Pedido(1, listaClientes.get(0), "piña", "4"));

        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaPedidos);
            oos.writeObject(listaClientes);
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de escritura");
        } finally {
            try {
                oos.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("error al cerrar el flujo");
            }
        }

    }

    public void leerObjeto(String path){
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            ArrayList<Pedido> pedidos = (ArrayList<Pedido>) ois.readObject();
            System.out.println("Pedidos");
            for (Pedido p : pedidos){
                System.out.println(p);
            }

            ArrayList<Cliente> clientes = (ArrayList<Cliente>) ois.readObject();
            System.out.println("Clientes");
            for (Cliente c : clientes){
                System.out.println(c);
            }

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        } finally {
            try {
                ois.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar el flujo");
            }
        }

    }
}
