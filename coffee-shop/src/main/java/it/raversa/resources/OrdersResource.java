package it.raversa.resources;


import it.raversa.be.CoffeeShop;
import it.raversa.model.CoffeeOrder;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Inject
    CoffeeShop coffeeShop;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoffeeOrder> getOrders() {
        return coffeeShop.getOrders();
    }

    @GET
    @Path("{id}")
    public CoffeeOrder getOrder(@PathParam("id") Long id) {
        return coffeeShop.getOrder(id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        try {
            coffeeShop.deleteOrder(id);
            return Response.ok().build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    public Response orderCoffee(@Valid @NotNull CoffeeOrder order) {
        final CoffeeOrder storedOrder = coffeeShop.orderCoffee(order);
        return Response.created(buildUri(storedOrder)).build();
    }

    private URI buildUri(CoffeeOrder order) {
        return UriBuilder.fromUri("http://localhost:9080/coffee-shop/resources/orders/{id}")
                .build(order.getId());
    }

    private JsonObject buildOrder(CoffeeOrder order) {
        return Json.createObjectBuilder()
                .add("type", order.getType().name())
                .add("status", order.getStatus().name())
                .add("_self", buildUri(order).toString())
                .build();
    }
}
