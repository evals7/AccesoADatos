package listarArchivos;

import listarArchivos.util.MetodosUtiles;

public class Main {
    public static void main(){
        MetodosUtiles util = new MetodosUtiles();
        //util.listarArchivos("C:\\Users\\Eva\\Documents");
        //util.listarArchivos2("C:\\Users\\Eva\\Documents");
        //util.leerTexto("src/resources/archivoTexto.txt");
        //util.encontrarPalabra("src/resources/archivoTexto.txt", "leer");
        //util.copiarContenido("src/resources/archivoTexto.txt", "src/resources/archivoSalida.txt");
        //util.informacionArchivo("src/resources/archivoSalida.txt");
        //util.numeroLineas("src/resources/archivoSalida.txt");
        util.escribirCsv("src/resources/estudiantes.csv");
    }
}
