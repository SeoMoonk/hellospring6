package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // em 만들기
        EntityManager em = emf.createEntityManager();

        // transaction -> commit
        // em.persist (영속화 해주세요)
        em.getTransaction().begin();

        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
