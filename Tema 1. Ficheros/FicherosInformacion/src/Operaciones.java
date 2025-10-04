import model.Usuario;

import java.io.*;
import java.util.ArrayList;

public class Operaciones {

    //METODO VOID PARA SACAR INFORMACION DE UN FICHERO O ARCHIVO
    public void informacionFichero(String path){

        //instanciamos el fichero lógico que puede o no existir, pero no dará null pointer
        File file = new File(path);

        //1. informacion que podemos sacar de file. Dira true si en el metodo, el archivo dado es real.
        System.out.println(file.exists());
        System.out.println(file.isFile());      //es archivo?
        System.out.println(file.isDirectory()); //es directorio?
        System.out.println(file.canWrite());
        System.out.println(file.canExecute());
        System.out.println(file.canRead());

        //2. crear un fichero (excepción: si yo intento crear un fichero, no tengo permisos de escritura.
        //una excepción es un escenario no contemplado que se pueden dar en ejecución
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("no se ha podido crear" + e.getCause()); //podemos reflejar la causa
            }
        }else{
            System.out.println("el fichero ya existe: " + file.getName());
        }



    }
    //METODO VOID PARA SACAR INFORMACIÓN DE UN DIRECTORIO O CARPETA
    public void informacionDirectorio(String path){
        File directorio = new File(path);

        if(directorio.isDirectory()){
            System.out.println("Vamos a trabajar con directorios");
            System.out.println("La ruta de este directorio es" + directorio.getAbsolutePath());  //ruta completa
            //3. Cuantos ficheros hay dentro de un directorio
            //3.1 sacar la lista de nombres que hay en la carpeta
            String [] nombreFicheros = directorio.list();
            for (String item : nombreFicheros){
                System.out.println(item);
            }

        }else if (!directorio.exists()){   //los directorios se crean con .mkdir().
            directorio.mkdir();            //Solo crea el directorio si la ruta padre existe
            directorio.mkdirs();           //crea la ruta completa si no existía
        }
    }

    public void mostrarFicherosRecurrentes(File file){
        //mostrar todos los archivos de una ruta y hacerlo de forma recursiva
        if (file.isDirectory()){
            System.out.println("el nombre de la carpeta es: " + file.getName());
            for (File item : file.listFiles()){
                mostrarFicherosRecurrentes(item);
            }
        }else{
            System.out.println("el nombre del archivo es: " + file.getName());
        }
    }



    //METODO VOID PARA ESCRIBIR EN UN FICHERO CON FILEWRITER
    public void escribirFichero(String path){
        File file = new File(path);                                 //1. instanciamos el objeto de File.
        if (!file.exists()){                                        //2. Si no existe, lo creamos, si existe, sale.
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error de creación de archivo: " + e.getCause());
            }
        }else{
            System.out.println("El fichero ya existe");
        }
        FileWriter fileWriter = null;           //3. Instanciamos un objeto de FileWriter y lo igualamos a null para declararlo un objeto separado dentro de un trycatch. El motivo es que es necesario cerrar el flujo con un "finally", y si el objeto queda dentro del trycath, no podría acceder a él
        try {
            fileWriter = new FileWriter(file);  //4. aquí terminamos de instanciar el objeto
            fileWriter.write(122); //7. ahora escribimos lo que queramos
        } catch (IOException e) {
            System.out.println("no puedes realizar la escritura para el fichero de clase");
        } finally {                             //5. el finally necesario para cerrar el flujo
            try {
                fileWriter.close();             //6. necesario cerrar el flujo, también nos salta una excepción para este
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //METODO ESCRITURA USANDO EL PRINTWRITER/BUFFEREDREADER
    public void escrituraSuperior(String path){
        File file = new File(path);             //1. instanciamos el File
        FileWriter fileWriter = null;           //2. instanciamos el FileWriter (igualado a null para que no se meta en la excepción)
        PrintWriter printWriter = null;         //3. instanciamos el PrintWriter (igualado a null para que no se meta en la excepción y podamos cerrar el flujo)
        //BufferedWriter bufferedWriter = null;   //4. instanciamos el BufferedWriter. Funciona igual qu eel PrintWriter, solo que es un poco menos eficiente, así que está aqui de ejemplo pero no lo vamos a usar

        try {
            fileWriter = new FileWriter (file); //5. dentro del trycatch, declaramos el file y el printwriter. Esto se podría hacer en una sola línea-> printWriter = new PrintWriter (fileWriter = new FileWriter (file));
            printWriter = new PrintWriter (fileWriter);
            printWriter.println("Esto es un ejemplo con printWriter");  //7. ahora podemos escribir con saltos de línea, println
            printWriter.println("Esto es una nueva línea");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {                              //6. hay que cerrar el flujo abierto. No nos obliga a capturar la excepción, pero es aconsejable porque puede dar error, así que capturamos la excepción de forma manual
            try {
                printWriter.close();
            } catch (NullPointerException e){
                System.out.println("error en el cerrado");
            }
        }
    }

    //METODO DE ESCRITURA PRINTWRITER PARA EXPORTAR USUARIOS DE UNA CLASE
                                                                        //hemos creado la Clase Usuario con sus variables privadas y el metodo toString modificado (ver metodo)
    public void exportarUsuario(String path){
        ArrayList<Usuario> listaUsuario = new ArrayList<>();            //1.creamos  un ArrayList de nombre listaUsuario y añadimos usuarios
        listaUsuario.add(new Usuario(1, "Eva", "López", "7894A", "eva@gmail.com"));
        listaUsuario.add(new Usuario(2, "Eva2", "López", "7894B", "eva@gmail.com"));
        listaUsuario.add(new Usuario(3, "Eva3", "López", "7894C", "eva@gmail.com"));
        listaUsuario.add(new Usuario(4, "Eva4", "López", "7894D", "eva@gmail.com"));
        listaUsuario.add(new Usuario(5, "Eva5", "López", "7894E", "eva@gmail.com"));
        listaUsuario.add(new Usuario(6, "Eva6", "López", "7894F", "eva@gmail.com"));

        File file = new File(path);                                     //3. instanciamos file, printWriter (a null) y printWriter (a null)
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);                          //4. inicializamos fileWriter y printWriter
            printWriter = new PrintWriter(fileWriter);
            printWriter.println("id, nombre, apellido, dni, correo");   //6. imprimimos la primera fila y recorremos la lista de usuarios con el for
            for (Usuario usuario: listaUsuario){                        //2. for each para recorrer el array Lista de usuario con cada usuario de tipo Usuario (esto es solo para ver en consola
                printWriter.println(usuario);
            }
        } catch (IOException e) {                                        //5. capturamos las excepciones y cerramos el flujo
            throw new RuntimeException(e);
        } finally{
            printWriter.close();
        }

    }

    //METODO DE LECTURA con FileReader
    public void lecturaFicheros(String path){
        File file = new File(path);                             //1. instanciamos file y fileReader(a null)
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);                  //2. declaramos fileReader y le pasamos file. Capturamos la excepción
            //fileReader.read();                                //4. aprovechamos para leer. Si queremos leer el archivo completo, tenemos que continuar la lectura hasta -1
            int lectorCodigo = 0;                               //5. para ello, tenemos que declarar una variable nueva = 0
            while ((lectorCodigo = fileReader.read()) != -1){   //6. mientras que ese número sea diferente de +1, sigue leyendo
                System.out.println(lectorCodigo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {                                             //3. aprovechamos para cerrar el flujo y capturar la excepción del fileReader.close()
            try {
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
