/**
 * 
 */
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

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

/**
 * @author Dan Swanson
 *
 */
@Path("/ifttt/v{version}")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
public class RootResource extends IftttResource {

    /**
     * @param template
     * @param defaultName
     */
    public RootResource(String template, String defaultName) {
        super(template, defaultName);
    }
    
    @GET
    @Path("/{func}")
    @Timed
    public IftttResponse getRequest(@Context HttpHeaders headers, @QueryParam("name") Optional<String> name, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers, false);
        final String value = String.format(getTemplate(), name.or(getDefaultName()));
        return new IftttResponse(new Saying(getCounter().incrementAndGet(), value));
    }

    @POST
    @Path("/{func}")
    @Timed
    public IftttResponse postRequest(@Context HttpHeaders headers, @FormParam("name") Optional<String> name, @PathParam("version") int version, @PathParam("func") String func) {
        validate(headers, false);
        final String value = String.format(getTemplate(), name.or(getDefaultName()));
        return new IftttResponse(new Saying(getCounter().incrementAndGet(), value));
    }

}
