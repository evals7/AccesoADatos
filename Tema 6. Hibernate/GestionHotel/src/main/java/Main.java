import model.Direccion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class Main {
    static void main(String[] args) {
        Trabajador trabajador = new Trabajador("Borja", "Martin", 10, "borja2@gmail.com", 20000,
                new Direccion("Madrid", "Madrid", "c/Madrid"));

        //1. Creamos la sesion factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //2. Creamos la sesión
        Session session = sessionFactory.openSession();
        //3. Creamos la transacción contra la bd
        Transaction transaction = session.beginTransaction();
        //4. realizamos la transacción correspondiente (insert into) y persistimos
        session.persist(trabajador);
        //5. dependiendo de cómo tengamos configuardo, realizamos el commit de la transacción
        transaction.commit();
        //6. cerramos la sesión y la sesion factory
        session.close();
        sessionFactory.close();

    }
}
