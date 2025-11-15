import controller.PeticionesController;
import model.Usuario;

import java.util.Scanner;

public class MainPeticiones {
    public static void main(String[] args){                 //VIEW: Interacciones con el usuario

        PeticionesController peticionesController = new PeticionesController();

        int opcion;
        Scanner scanner = new Scanner(System.in);

        do{
        System.out.println("Sistema de gestion de usuarios ");
        System.out.println("1. Insertar usuario");
        System.out.println("2. Borrar usuario");
        System.out.println("3. Listar usuarios");
        System.out.println("4. Exportar usuarios a XML");
        System.out.println("5. Exportar usuarios a JSON");
        System.out.println("6. Importar usuarios desde JSON");
        System.out.println("7. Importar usuarios desde XML");
        System.out.println("8. Salir");
        System.out.println("Indica qué quieres hacer");

        opcion = scanner.nextInt();

        switch (opcion){
            case 1->{
                System.out.println("Inserta el nombre");
                String nombre = scanner.next();
                System.out.println("Inserta el correo");
                String correo = scanner.next();
                System.out.println("Inserta el telefono");
                int telefono = scanner.nextInt();
                System.out.println("Inserta el perfil");
                int perfil = scanner.nextInt();

                peticionesController.insertarUsuario(new Usuario(nombre, correo, telefono, perfil));
            }
            case 2->{
                System.out.println("Indica el ID del usuario que quieres borrar");
                int id = scanner.nextInt();
                peticionesController.borrarUsuario(id);
            }
            case 3->{
                System.out.println("Datos:");
                peticionesController.listarUsuarios();
            }
            case 4->{
                System.out.println("Exportación a XML");
                peticionesController.exportacionXML();
            }
            case 5->{
                System.out.println("Exportamos usuarios a un JSON");
                peticionesController.exportarJSON();
            }
            case 6->{
                System.out.println("Importar usuarios de un JSON");
                peticionesController.importarJSON();
            }
            case 7->{
                System.out.println("Importar usuarios de un XML");
                peticionesController.importacionXML();
            }
        }

        } while (opcion!=8);

    }
}
