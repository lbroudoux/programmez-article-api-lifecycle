package com.redhat.samples.fruits.rest;

import com.redhat.fruits.catalog.dto.FruitDTO;
import com.redhat.fruits.catalog.dto.FruitRequestDTO;
import com.redhat.samples.fruits.domain.Fruit;
import com.redhat.samples.fruits.domain.Origin;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/fruits")
/**
 * JAX-RS resource exposing Fruits Catalog API.
 * @author laurent
 */
public class FruitsResource {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   FruitMapper fruitMapper;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<FruitDTO> listFruits() {
      logger.info("Listing fruits...");
      return fruitMapper.toResources(Fruit.listAll());
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Transactional
   public Response createFruit(FruitRequestDTO fruitRequest) {
      logger.infof("Creating new fruit: %s", fruitRequest.toString());
      Fruit fruit = fruitMapper.fromResource(fruitRequest);
      fruit.persist();
      return Response.status(Response.Status.CREATED).entity(fruitMapper.toResource(fruit)).build();
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public FruitDTO getFruit(@PathParam("id") String id) {
      logger.infof("Getting fruit with id '%s'", id);
      return fruitMapper.toResource(Fruit.findById(id));
   }

   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public FruitDTO updateFruit(@PathParam("id") String id, FruitRequestDTO fruitRequest) {
      logger.infof("Updating fruit '%s' with: %s", id, fruitRequest.toString());
      Fruit fruit = Fruit.findById(id);
      fruit.name = fruitRequest.getName();
      fruit.origin = Origin.fromValue(fruitRequest.getOrigin().getValue());
      fruit.persist();
      return fruitMapper.toResource(fruit);
   }

   @DELETE
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Transactional
   public Response deleteFruit(@PathParam("id") String id) {
      logger.infof("Deleting fruit with id '%s'", id);
      Fruit fruit = Fruit.findById(id);
      if (fruit != null) {
         fruit.delete();
      }
      return Response.status(Response.Status.NO_CONTENT.getStatusCode(), id + " is no longer present").build();
   }
}
