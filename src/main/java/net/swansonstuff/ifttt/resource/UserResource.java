/**
 * 
 */
package net.swansonstuff.ifttt.resource;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import net.swansonstuff.ifttt.response.UserResponse;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Optional;

/**
 * @author Dan Swanson
 *
 */
@Path("/ifttt/v{version}")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
public class UserResource extends IftttResource {

    /**
     * @param template
     * @param defaultName
     */
    public UserResource(String template, String defaultName) {
        super(template, defaultName);
    }
    
    @GET
    @Path("/user/{func}")
    @Timed
    public UserResponse getUserRequest(@Context HttpHeaders headers, @QueryParam("name") Optional<String> name, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers);
        final String value = String.format(getTemplate(), name.or(getDefaultName()));
        return new UserResponse(value, UUID.randomUUID().toString(), "http://swansonstuff.net/"+value);
    }

    @POST
    @Path("/user/{func}")
    @Timed
    public UserResponse postUserRequest(@Context HttpHeaders headers, ObjectNode data, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers);
        JsonNode name = data.get("name");
        final String value = String.format(getTemplate(), (name != null ? name.asText() : getDefaultName()));
        return new UserResponse(value, UUID.randomUUID().toString(), "http://swansonstuff.net/"+value);
    }

}
