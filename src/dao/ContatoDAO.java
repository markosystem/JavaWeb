package dao;

import factory.JPAFactory;
import model.Contato;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Marcos on 18/10/2016.
 */
public class ContatoDAO {
    /**
     * Instância do EntityManager para conexão com banco
     */
    private static EntityManager em;

    /**
     * Método de acesso ao EntityManager
     * @return EntityManager
     */
    public static EntityManager getEm(){
        if(em == null)
            em = JPAFactory.getEntityManager();
        return em;
    }

    /**
     * Método que adiciona ou editar um Contato
     * @param contato
     * @return boolean
     */
    public static boolean save(Contato contato){
        try{
            getEm().getTransaction().begin();
            getEm().persist(contato);
            getEm().getTransaction().commit();
            return true;
        }catch (Exception e){
            if(getEm().getTransaction().isActive())
                getEm().getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método que busca um Contato a partir de um identificador
     * @param id
     * @return Contato
     */
    public static Contato buscar(Integer id) {
        try {
            return getEm().getReference(Contato.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método que deleta um contato no banco
     * @param entity
     * @return boolean
     */
    public static boolean delete (Contato entity){
        try{
            getEm().getTransaction().begin();
            getEm().remove(buscar(entity.getId()));
            getEm().getTransaction().commit();
            return true;
        }catch (Exception e){
            if(getEm().getTransaction().isActive())
                getEm().getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método que retorna uma lista de Contatos do banco de dados.
     * @return List<Contato>
     */
    public static List<Contato> getList(){
        Query query = getEm().createQuery("SELECT c FROM Contato c ORDER BY c.nome ASC");
        return query.getResultList();
    }

    /**
     * Método que retorna uma lista de Contatos do banco de dados, utilizando o conceito de Criteria
     * @return List<Contato>
     *
    public List<Contato> getListCriteria() {
        EntityManager em = JPAFactory.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Contato> query = criteriaBuilder.createQuery(Contato.class);
        Root root = query.from(Contato.class);
        query.orderBy(criteriaBuilder.asc(root.get("nome")));
        TypedQuery<Contato> query2 = em.createQuery(query);
        return query2.getResultList();
    }
    */
}
