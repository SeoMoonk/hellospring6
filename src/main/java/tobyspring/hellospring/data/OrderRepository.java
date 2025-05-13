package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tobyspring.hellospring.order.Order;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(order);

        em.getTransaction().commit();
        em.close();
    }
}
