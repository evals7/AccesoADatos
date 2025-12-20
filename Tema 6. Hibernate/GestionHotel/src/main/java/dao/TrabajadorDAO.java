package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jdk.swing.interop.SwingInterOpUtils;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;

public class TrabajadorDAO {

    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    public void insertarUsuario(Trabajador trabajador){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        session.persist(trabajador);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void eliminarUsuario(int id){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Trabajador trabajador = session.find(Trabajador.class, id);
        session.remove(trabajador);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public Trabajador getTrabajador(int id){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Trabajador trabajador = session.find(Trabajador.class, id);

        transaction.commit();
        session.close();
        sessionFactory.close();
        return trabajador;
    }

    public void actualizarUsuario(int id, int salario){
        Trabajador trabajador= getTrabajador(id);
        System.out.println(trabajador.getNombre());
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        trabajador.setSalario(salario);
        session.merge(trabajador);      //tenemos que hacer merge en el usuario para que se junten el viejo y el nuevo sin cerrar la transacción de getTrabajador

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void seleccionarTrabajador(String correoBuscar){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Query<Trabajador> query = session.createNamedQuery("Trabajador.getByEmail", Trabajador.class);
        query.setParameter("mail", correoBuscar);
        List<Trabajador> listaResultado = query.getResultList();
        for (Trabajador trabajador :listaResultado){
            System.out.println("Los datos del trabajador son:");
            trabajador.mostrarDatos();
        }

        //comentamos esto porque hemos creado la query en una anotiación de la clase Trabajador
        //SELECT * FROM empleados where email=email
        //session.createMutationQuery("FROM Trabajador t WHERE t.correo=:email").setParameter("email", email); //los datos de la query cogemos los del objeto y lo igualamos a la entidad de la bd mediante etiquetado (reemplazar etiqueta por atributo)
        //session.createMutationQuery("FROM Trabajador t WHERE t.correo=:?").setParameter(1,email); //si trabajamos con posiciones sería así, pero es más complicado

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void seleccionarTodos(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Query<Trabajador> query = session.createNamedQuery("Trabajador.getAll" , Trabajador.class);
        List<Trabajador> listaResultados = query.getResultList();
        for (Trabajador trabajador :listaResultados){
            trabajador.mostrarDatos();
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    //METODO CON CRITERIA SELECT
    public void actualizarCriteria(String correo, int salario){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        //HQL: UPDATE FROM empleados SET salario s = salario WHERE correo c = correo
        //namedQuery : igual que la hql
        //criteria: necesitamos un objeto criteriaBuilder, otro criteriaUpdate y un objeto root y mutationQuery
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<Trabajador> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Trabajador.class);
            //FROM empleados -> mapeo a la clase asociada
        Root<Trabajador> root = criteriaUpdate.getRoot();
            //SET
            //WHERE
        criteriaUpdate.set(root.get("salario"), salario)
                .where(criteriaBuilder.equal(root.get("correo"), correo));
        MutationQuery query = session.createMutationQuery(criteriaUpdate);
        int row = query.executeUpdate();
        System.out.println("El numero de filas afectadas es de " + row);

        transaction.commit();
        session.close();
        sessionFactory.close();


    }
}
