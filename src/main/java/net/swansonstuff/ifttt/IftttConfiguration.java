/**
 * 
 */
package net.swansonstuff.ifttt;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Dan Swanson
 *
 */
public class IftttConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";
    
    @NotEmpty
    private String iftttKey;

    /**
     * 
     */
    public IftttConfiguration() {
        // TODO Auto-generated constructor stub
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    
    /**
     * @return the iftttKey
     */
    public String getIftttKey() {
        return this.iftttKey;
    }

    
    /**
     * @param iftttKey the iftttKey to set
     */
    public void setIftttKey(String iftttKey) {
        this.iftttKey = iftttKey;
    }

}
