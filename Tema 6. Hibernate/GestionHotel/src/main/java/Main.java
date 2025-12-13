import model.Direccion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class Main {
    static void main(String[] args) {
        Trabajador trabajador = new Trabajador("Borja", "Martin", 10, "borja@gmail.com", 20000,
                new Direccion("Madrid", "Madrid", "c/Madrid"));

        //1. Creamos la sesion factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //2. Creamos la sesión
        Session session = sessionFactory.openSession();
        //3. Creamos la transacción contra la bd
        Transaction transaction = session.beginTransaction();
        //4. realizamos la transacción correspondiente (insert into) y persistimos:
        //4.1 CREATE
        //session.persist(trabajador);

        //4.2 SELECT -> ID/WHERE
        /*
        Trabajador trabajadorSelect = session.find(Trabajador.class, 1); //find(Objeto.class, id)
        if(trabajadorSelect != null){
            System.out.println("Los datos del trabajador son");
            System.out.println(trabajadorSelect.getNombre());
            System.out.println(trabajadorSelect.getAntiguedad());
            System.out.println(trabajadorSelect.getSalario());
            System.out.println(trabajadorSelect.getCorreo());
        }
        */

        //4.3 UPDATE -> ID/WHERE
        /*
        Trabajador trabajadorUpdate = session.find(Trabajador.class, 1);
        trabajadorUpdate.setSalario(40000);
        */

        //4.4 DELETE -> ID/WHERE
        Trabajador trabajadorDelete = session.find(Trabajador.class, 1);
        session.remove(trabajadorDelete);


        //5. dependiendo de cómo tengamos configuardo, realizamos el commit de la transacción
        transaction.commit();
        //6. cerramos la sesión y la sesion factory
        session.close();
        sessionFactory.close();

    }
}
