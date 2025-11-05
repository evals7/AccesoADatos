import controller.PeticionesController;
import model.Usuario;

public class MainPeticiones {
    public static void main(String[] args){                 //VIEW: Interacciones con el usuario

        PeticionesController peticionesController = new PeticionesController();
        peticionesController.insertarUsuario(new Usuario("BorjaM", "borjaM@gmail.com", 123, 1));
    }
}
