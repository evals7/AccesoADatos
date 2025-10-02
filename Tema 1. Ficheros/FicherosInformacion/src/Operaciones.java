import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void mostrarFicherosRecurrentes(){
        //mostrar todos los archivos de una ruta y hacerlo de forma recursiva
    }

    //METODO VOID PARA ESCRIBIR EN UN FICHERO
    public void escribirFichero(String path){
        File file = new File(path); //1. instanciamos el file. Si no existe, lo creamos, si existe, sale.
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error de creación de archivo: " + e.getCause());
            }
        }else{
            System.out.println("El fichero ya existe");
        }
        FileWriter fileWriter = null; //2. Instanciamos un objeto de FileWriter y lo igualamos a null para darle un objeto separado y que salte la excepción. Necesitamos la excepción para añadir el "finally" que solo puede ir tras un try o catch
        try {
            fileWriter = new FileWriter(file); //3. aquí terminamos de instanciar el objeto
            fileWriter.write("esto es una prueba de escritura"); //6. ahora escribimos lo que queramos
        } catch (IOException e) {
            System.out.println("no puedes realizar la escritura para el fichero de clase");
        } finally {  //4. el finally necesario para cerrar el flujo
            try {
                fileWriter.close();  //5. necesario cerrar el flujo, también nos salta una excepción para este
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
