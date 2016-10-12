package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

interface Resource <T> {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<?> getAll() throws SQLException, ClassNotFoundException;

    @GET
    @Path("/{id}")
    T get(@PathParam("id") int id ) throws SQLException, ClassNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response add(T t) throws SQLException, ClassNotFoundException;

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") int id) throws SQLException, ClassNotFoundException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(T t) throws SQLException, ClassNotFoundException;

}
