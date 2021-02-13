package it.raversa.be;

import it.raversa.dao.Orders;
import it.raversa.model.CoffeeOrder;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CoffeeShop {
    @Inject
    Orders orders;

    public List<CoffeeOrder> getOrders() {
        return orders.retrieveAll();
    }

    public CoffeeOrder getOrder(Long id) {
        return orders.retrieve(id);
    }

    @TransactionAttribute
    public CoffeeOrder orderCoffee(CoffeeOrder order) {
        orders.store(order);
        return order;
    }

    @TransactionAttribute
    public void deleteOrder(Long id) {
        orders.delete(id);
    }
}
