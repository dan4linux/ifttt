/**
 * 
 */
package net.swansonstuff.ifttt;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Dan Swanson
 *
 *Use the following set of HTTP response status codes:<br>
<br>STATUS  DESCRIPTION
<ul>
<li>200 The request was a success.
<li>400 There was something wrong with incoming data from IFTTT. Provide an error response body to clarify what went wrong.
<li>401 IFTTT sent an OAuth2 access token that isn’t valid.
<li>404 IFTTT is trying to reach a URL that doesn’t exist.
<li>500 There was an error in your application logic.
<li>503 Your service is not available at the moment, but IFTTT should try again later.
</ul>
 */

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<WebApplicationException> {
    
    public ExceptionMapper() {        
    }

    public Response toResponse(WebApplicationException ex) {
        return Response.status(401)
                .entity(new IftttErrorMessage(ex.getMessage()))
                .type(MediaType.APPLICATION_JSON).
                build();
    }

}

