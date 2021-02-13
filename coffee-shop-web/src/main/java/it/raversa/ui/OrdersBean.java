package it.raversa.ui;

import it.raversa.client.OrdersClient;
import it.raversa.client.UnknownUrlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.Serializable;

@Named("ordersBean")
@SessionScoped
public class OrdersBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(OrdersBean.class.getName());
    @Inject
    @RestClient
    private OrdersClient ordersClient;

    private String type;
    private String status;

    public void orderCoffee() {
        LOGGER.debug("orderCoffee {}", this);
        JsonObject order = Json.createObjectBuilder().add("type", type).build();
        try {
            ordersClient.orderCoffee(order);
        } catch (UnknownUrlException e) {
            LOGGER.error("The given URL is unreachable", e);
        }
    }

    public JsonArray getOrders() {
        try {
            return ordersClient.getOrders();
        } catch (UnknownUrlException e) {
            LOGGER.error("The given URL is unreachable");
            return null;
        }
    }

    public void deleteOrder(Long id) {
        try {
            LOGGER.error("delete order {}", id);
            ordersClient.deleteOrder(id);
        } catch (UnknownUrlException e) {
            LOGGER.error("The given URL is unreachable");
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrdersBean{");
        sb.append("ordersClient=").append(ordersClient);
        sb.append(", type='").append(type).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
