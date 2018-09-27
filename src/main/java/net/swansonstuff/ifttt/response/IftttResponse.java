/**
 * 
 */
package net.swansonstuff.ifttt.response;


/**
 * @author Dan Swanson
 *
 *Response body format
<br>Provide response bodies as JSON objects. Success responses have a top-level wrapper object called data.
<br>
<br>Raw body on success
<pre>
 {
  "data": {
    // The value of `data` varies, but is typically
    // either an object or array
    ...
  }
}
</pre>
 */
public class IftttResponse {
    
    private final Object data;

    /**
     * The object for the response
     * @param data 
     * 
     */
    public IftttResponse(Object data) {
        this.data = data;
    }
    
    /**
     * Return the data object
     * @return the data object
     */
    public Object getData() {
        return data;
    }

}
