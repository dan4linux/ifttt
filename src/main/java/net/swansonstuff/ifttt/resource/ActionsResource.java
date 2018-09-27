package net.swansonstuff.ifttt.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import net.swansonstuff.ifttt.Saying;
import net.swansonstuff.ifttt.response.IftttResponse;
import net.swansonstuff.ifttt.response.IftttResponseArray;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Optional;

@Path("/ifttt/v{version}")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
public class ActionsResource extends IftttResource {

    public ActionsResource(String template, String defaultName) {
        super(template, defaultName);
    }

    @GET
    @Path("/actions/{func}")
    @Timed
    public IftttResponseArray getActionsRequest(@Context HttpHeaders headers, @QueryParam("name") Optional<String> name, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers);
        final String value = String.format(getTemplate(), name.or(getDefaultName()));
        return new IftttResponseArray(new Saying(getCounter().incrementAndGet(), value));
    }

    @POST
    @Path("/actions/{func}")
    @Timed
    public IftttResponseArray postActionsRequest(@Context HttpHeaders headers, ObjectNode data, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers);
        JsonNode name = data.get("name");
        final String value = String.format(getTemplate(), (name != null ? name.asText() : getDefaultName()));
        return new IftttResponseArray(new Saying(getCounter().incrementAndGet(), value));
    }

}