import controller.DrinkController;

import java.util.Scanner;

public class Main {
                                                        //4. Clase con la parte de la vista con la que interactua el usuario, con el metodo main
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);       //4.1 Instanciamos Scanner para que el usuario interactue en consola
        DrinkController controller = new DrinkController(); //4.4 instanciamos el controller para que cuando accedamos al objeto, podamos acceder a todas sus funcionalidades aka métodos
        int opcion = 0;
        do {                                            //4.2 menú con doWhile: mientras se cumpla la condición de no salida(4), imprime eso
            System.out.println("BUSCADOR DE CÓCTELES");
            System.out.println("1. Buscar por nombre");
            System.out.println("2. Buscar por letra");
            System.out.println("3. Buscar aleatoria");
            System.out.println("4. Salir");
            System.out.println("¿Qué quieres hacer?");
            opcion = scanner.nextInt();
            switch (opcion){                            //4.3 según la elección hacer x o y
                case 1 ->{
                    System.out.println("BÚSQUEDA POR NOMBRE");
                    System.out.println("¿Por qué nombre quieres buscar?");
                    String nombre = scanner.next();
                    //controller.consultarNombre(nombre.toLowerCase());   //4.5 hemos Declarado un string de nombre y lo vamos a buscar con el metodo consultarNombre de DrinController
                    controller.consultarLetraNombre(nombre.toLowerCase(), "s");
                }
                case 2 ->{
                    System.out.println("BÚSQUEDA POR LETRA");
                    System.out.println("¿Por qué letra quieres buscar?");
                    String letra = scanner.next();
                    //controller.consultarLetra(letra.toLowerCase());
                    controller.consultarLetraNombre(letra.toLowerCase(), "f");
                }
                case 3 ->{
                    System.out.println("BÚSQUEDA ALEATORIA");
                    controller.obtenerAleatorio();
                }
                case 4 ->{
                    System.out.println("SALIENDO");
                }
            }

        }while (opcion!=4);
    }
}
