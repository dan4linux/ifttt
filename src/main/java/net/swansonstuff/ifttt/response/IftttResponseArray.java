/**
 * 
 */
package net.swansonstuff.ifttt.response;

import jersey.repackaged.com.google.common.collect.Lists;

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
public class IftttResponseArray {
    
    private final Object[] data = new Object[1];

    /**
     * The object for the response
     * @param data 
     * 
     */
    public IftttResponseArray(Object data) {
        this.data[0] = data;
    }
    
    /**
     * Return the data object
     * @return the data object
     */
    public Object[] getData() {
        return data;
    }

}
