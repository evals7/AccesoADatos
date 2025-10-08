import java.io.File;

public class Entrada {

    public static void main() {
        Operaciones operaciones = new Operaciones();
        //operaciones.informacionFichero("src/resources/ejemplo1.txt");
        //operaciones.informacionDirectorio("src/resources");
        //operaciones.mostrarFicherosRecurrentes(new File("C:\\Users\\Eva\\OneDrive\\DAM\\2ºDAM\\06 Acceso a datos\\AccesoADatos\\Tema 1. Ficheros\\FicherosInformacion"));
        //operaciones.escribirFichero("src/resources/escritura.txt");
        //operaciones.escrituraSuperior("src/resources/escritura_superior.txt");
        //operaciones.exportarUsuario("src/resources/usuarios.csv");
        //operaciones.lecturaFicheros("src/resources/escritura.txt");
        //operaciones.escribirObjeto("src/resources/usuario.dat");
        //operaciones.leerObjeto("src/resources/usuario.dat");
        operaciones.importarCSV("FicherosInformacion/src/resources/usuarios.csv");
    }
}
