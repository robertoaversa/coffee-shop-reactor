// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package it.raversa.client;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.Dependent;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Dependent
@RegisterRestClient
@RegisterProvider(UnknownUrlExceptionMapper.class)
@RegisterProvider(BadRequestExceptionMapper.class)
@Path("/orders")
public interface OrdersClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getOrders() throws UnknownUrlException;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getOrder(@PathParam("id") Long id) throws UnknownUrlException;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response orderCoffee(JsonObject order) throws UnknownUrlException;

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") Long id) throws UnknownUrlException;


}
