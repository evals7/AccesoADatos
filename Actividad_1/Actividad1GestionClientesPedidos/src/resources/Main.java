package resources;

public class Main {

    public static void main(String[] args) {
        Operaciones operaciones = new Operaciones();

        //operaciones.grabarObjeto("src/resources/datos.csv");
        //operaciones.escribirObjeto("src/resources/datos.dat");
        operaciones.leerObjeto("src/resources/datos.dat");

    }
}
