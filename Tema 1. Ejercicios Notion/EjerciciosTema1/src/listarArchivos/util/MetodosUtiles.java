package listarArchivos.util;

import model.Estudiante;

import java.io.*;
import java.util.ArrayList;

public class MetodosUtiles {

    //1. Desarrolla un programa que muestre una lista de todos los archivos (no directorios) que se encuentran en una carpeta especificada por el usuario.
    public void listarArchivos(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("Listado de archivos de la carpeta");
                File[] nombreArchivos = file.listFiles();
                for (File item : nombreArchivos) {
                    if (item.isFile()) {
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

    //1.1
    public void listarArchivos2(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listadoArchivos = file.listFiles();
                for (File archivo : listadoArchivos) {
                    if (archivo.isFile()) {
                        System.out.println(archivo);
                    }
                }
            }
        }
    }

    //2. Leer un archivo de texto: Escribe un programa que lea el contenido de un archivo de texto existente y lo muestre por consola. El programa debe solicitar al usuario la ruta del archivo a leer.
    public void leerTexto(String path){
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = bufferedReader.readLine())!= null){
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se puede leer el archivo");
        } catch (IOException e) {
            System.out.println("error de lectura");
        } finally{
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("No tienes permisos");
            }
        }


    }

    //3. Escribe un programa que busque cuántas veces aparece una palabra específica en un archivo de texto. El usuario debe introducir la ruta del archivo y la palabra a buscar.
    public void encontrarPalabra(String path, String palabra){
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;
            int i = 0;
            while ((linea = bufferedReader.readLine()) != null){
                String[] palabras = linea.split(" ");
                for (String p: palabras ) {
                    if(p.equals(palabra)){
                        i++;
                    }
                }
            } System.out.println("La palabra "+ palabra + "aparece: " + i + " veces");

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se puede leer");
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getCause());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("no tienes permisos de lectura");
            }
        }

    }

    //4. Crea un programa que copie el contenido de un archivo a otro. El programa debe solicitar al usuario la ruta del archivo de origen y la ruta del archivo de destino.
    public void copiarContenido(String origen, String destino){
        File file = new File(origen);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        ArrayList<String> lineas = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea="";
            while ((linea = bufferedReader.readLine())!= null) {
                System.out.println(linea);
                lineas.add(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No tienes permisos para leer");
        } catch (IOException e) {
            System.out.println("Error");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error en la lectura: "+ e.getCause());
            }
        }

        File file2 = new File(destino);
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file2));
            for (String l: lineas){
                printWriter.write(l + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            printWriter.close();
            System.out.println("Archivo copiado");
        }

    }

    //5. Desarrolla un programa que obtenga y muestre información detallada sobre un archivo especificado por el usuario, como su tamaño, fecha de última modificación, permisos de lectura/escritura y si es un archivo oculto o no.
    public void informacionArchivo(String path){
    File file = new File(path);
    if(!file.isDirectory()){
        System.out.println("Nombre del archivo: "+ file.getName());
        System.out.println("Tamaño del archivo: "+ file.length());
        System.out.println("Fecha de la última modificación: "+ file.lastModified());
        System.out.println("Oculto: " + file.isHidden());
        System.out.println("Permisos de escritura: "+ file.canWrite());
        System.out.println("Permisos de lectura: "+ file.canRead());
    }
    }

    //6. Desarrolla un programa que cuente el número de líneas que contiene un archivo de texto. El programa debe solicitar al usuario la ruta del archivo a analizar.
    public void numeroLineas(String path){
    File file = new File(path);
    BufferedReader bufferedReader = null;
    ArrayList<String> lineas = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;
            int contador=0;
            while ((linea = bufferedReader.readLine())!= null){
                lineas.add(linea);
            }
            for (String l: lineas){
                contador++;
            }
            System.out.println("Lineas que contiene el archivo: " + contador);


        } catch (FileNotFoundException e) {
            System.out.println("No tiene permisos de lectura");
        } catch (IOException e) {
            System.out.println("Error en la lectura: "+e.getCause());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error de lectura");
            }
        }

    }

    //7. Escribe un programa que cree un archivo CSV (valores separados por comas) con información de 3 estudiantes. Para cada estudiante, guarda su nombre, edad y calificación. Luego, lee el archivo y muestra su contenido en la consola.
    public void escribirCsv(String path){
        File file = new File(path);
        PrintWriter printWriter = null;

        Estudiante est1= new Estudiante("Jacobo", 36, 9);
        Estudiante est2= new Estudiante("Christian", 20, 5);
        Estudiante est3= new Estudiante("Paco", 40, 10);

        try {
            printWriter = new PrintWriter(new FileWriter(file));
            printWriter.write("Jacobo", 36, 9);
        } catch (IOException e) {
            System.out.println("no tienes permisos de escritura");
        } finally {
            printWriter.close();
        }






    }
}