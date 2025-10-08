package listarArchivos.util;

import java.io.File;

public class MetodosUtiles {

    //Desarrolla un programa que muestre una lista de todos los archivos (no directorios) que se encuentran en una carpeta especificada por el usuario.
    public void listarArchivos(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("Listado de archivos de la carpeta");
                File[] nombreArchivos = file.listFiles();
                for (File item : nombreArchivos) {
                    if(item.isFile()){
                        System.out.println(item.getName());
                    }
                }
            } else {
                System.out.println("La carpeta no contiene archivos");
            }
        } else {
            System.out.println("La carpeta no existe");
        }
    }
}