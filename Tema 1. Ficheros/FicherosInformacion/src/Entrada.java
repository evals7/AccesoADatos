import java.io.File;

public class Entrada {

    public static void main() {
        Operaciones operaciones = new Operaciones();
        //operaciones.informacionFichero("src/resources/ejemplo1.txt");
        //operaciones.informacionDirectorio("src/resources");
        //operaciones.escribirFichero("src/resources/escritura.txt");
        operaciones.mostrarFicherosRecurrentes(new File("C:\\Users\\Eva\\OneDrive\\DAM\\2ºDAM\\06 Acceso a datos\\AccesoADatos\\Tema 1. Ficheros\\FicherosInformacion"));
    }
}
