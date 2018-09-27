package net.swansonstuff.ifttt.resource;

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
import net.swansonstuff.ifttt.response.IftttResponseArray;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/ifttt/v{version}")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
public class TriggersResource extends IftttResource {

    private static final String ACTIONS_OPTIONS = "options";

    public TriggersResource(String template, String defaultName) {
        super(template, defaultName);
    }

    @GET
    @Path("/triggers/{triggerSlug}")
    @Timed
    public IftttResponseArray getTriggersRequest(@Context HttpHeaders headers, @QueryParam("triggerSlug") String triggerSlug, @PathParam("version") int version) {
        validate(headers);
        final String value = String.format(getTemplate(), triggerSlug);
        return new IftttResponseArray(new Saying(getCounter().incrementAndGet(), value));
    }

    @POST
    @Path("/triggers/{triggerSlug}")
    @Timed
    public IftttResponseArray postTriggersRequest(@Context HttpHeaders headers, ObjectNode data, @PathParam("version") int version, @PathParam("triggerSlug") String triggerSlug) {
        validate(headers);
        JsonNode name = data.get("name");
        final String value = String.format(getTemplate(), (name != null ? name.asText() : getDefaultName()));
        return new IftttResponseArray(new Saying(getCounter().incrementAndGet(), value));
    }
    
    @POST
    @Path("/triggers/{{triggerSlug}}/fields/{{triggerFieldSlug}}/{action}")
    public IftttResponseArray postTriggersFieldsRequest(@Context HttpHeaders headers, ObjectNode data, @PathParam("version") int version, @PathParam("triggerName") String triggerName, @PathParam("action") String action) {
        switch(action) {
            case ACTIONS_OPTIONS:
                
                break;
            default:
        }
        return null;
    }

}