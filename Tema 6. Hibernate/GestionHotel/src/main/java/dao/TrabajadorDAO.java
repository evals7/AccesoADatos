package dao;

import jdk.swing.interop.SwingInterOpUtils;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
}
