package it.raversa.dao;

import it.raversa.model.CoffeeOrder;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class Orders {
    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public List<CoffeeOrder> retrieveAll() {
        return em.createNamedQuery("CoffeeOrder.findAll", CoffeeOrder.class).getResultList();
    }

    public CoffeeOrder retrieve(Long id) {
        return em.find(CoffeeOrder.class, id);
    }

    public void store(CoffeeOrder order) {
        em.persist(order);
    }


    public void delete(Long id) {
        em.remove(em.getReference(CoffeeOrder.class, id));
    }
}
