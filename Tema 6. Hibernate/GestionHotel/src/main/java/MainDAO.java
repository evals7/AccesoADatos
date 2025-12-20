import dao.TrabajadorDAO;
import model.Direccion;
import model.Trabajador;

public class MainDAO {
    static void main(String[] args) {
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

        /*
        trabajadorDAO.insertarUsuario(new Trabajador("Carmen", "Lopez", 3, "carmen@gmail.com", 40000,
        new Direccion("Sevilla", "Sevilla", "c/Sevilla")));
        */

        /*
        trabajadorDAO.eliminarUsuario(1);
        */
        /*
        trabajadorDAO.actualizarUsuario(4, 45000);
        */
        /*
        trabajadorDAO.seleccionarTrabajador("borja@gmail.com");
        */
        trabajadorDAO.seleccionarTodos();
    }

}
