package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Marcos on 18/10/2016.
 */
public class JPAFactory {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Agenda");
    private JPAFactory(){}

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
