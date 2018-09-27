/**
 * 
 */
package net.swansonstuff.ifttt.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * @author Dan Swanson
 *
 */
public class UserResponse extends IftttResponse {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_URL = "url";
    private static final String FIELD_ID = "id";
    private static final ObjectMapper mapper = new ObjectMapper();
    volatile ObjectNode data;

    public UserResponse() {
        super(null);
    }
    
    public UserResponse(String name, String id, String url){
        super(mapper.createObjectNode());
        ObjectNode data = (ObjectNode) getData();
        data.set(FIELD_NAME, TextNode.valueOf(name));
        data.set(FIELD_ID, TextNode.valueOf(id));
        data.set(FIELD_URL, TextNode.valueOf(url));
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return this.data.get(FIELD_ID).asText();
    }

    
    /**
     * @return the url
     */
    public String getUrl() {
        return this.data.get(FIELD_URL).asText();
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return this.data.get(FIELD_NAME).asText();
    }

}
