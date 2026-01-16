import dao.ClienteDAO;
import dao.PerfilesDAO;
import dao.TrabajadorDAO;
import model.Cliente;
import model.Direccion;
import model.Trabajador;

public class MainDAO {
    static void main(String[] args) {
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        PerfilesDAO perfilDAO = new PerfilesDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        perfilDAO.getPerfil(1);
        perfilDAO.getTipoPerfil(2);
        //clienteDAO.addCliente(new Cliente("BorjaCliente"));
        //clienteDAO.addReserva(1, 3);
        trabajadorDAO.verReservasRealizadas(3);
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
        /*
        trabajadorDAO.seleccionarTodos();
        */
        Trabajador trabajador = trabajadorDAO.getTrabajador(4);
        System.out.println("El perfil del trabajador es " +trabajador.getNombre());
    }

}
