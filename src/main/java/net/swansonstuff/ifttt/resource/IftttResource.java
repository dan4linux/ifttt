package net.swansonstuff.ifttt.resource;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
import net.swansonstuff.ifttt.Validators;
import net.swansonstuff.ifttt.response.IftttResponse;
import net.swansonstuff.ifttt.response.IftttResponseArray;
import net.swansonstuff.ifttt.response.UserResponse;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IftttResource {

    private final String template;
    final String defaultName;
    private final AtomicLong counter;

    public IftttResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }
    
    public String getDefaultName() {
        return defaultName;
    }
    
    public String getTemplate() {
        return template;
    }
    
    public AtomicLong getCounter() {
        return counter;
    }
    
    public void validate(HttpHeaders headers) {
        Validators.validate(headers);
    }

}
