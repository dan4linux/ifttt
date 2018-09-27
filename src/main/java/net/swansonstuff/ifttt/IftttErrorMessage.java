/**
 * 
 */
package net.swansonstuff.ifttt;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Dan Swanson
 *
 *Error responses have a top-level errors array. Each element of errors is an object with a message property whose value is a user-friendly error message.
<br>Raw body on error
<pre>
 {
  "errors": [
    {
      "message": "Something went wrong!"
    }
  ]
}
</pre>
 */
public class IftttErrorMessage {
    private List<String> errors = Lists.newArrayListWithCapacity(1);

    public IftttErrorMessage(String message) {
        errors.add(message);
    }
    
    public List<String> getErrors() {
        return errors;
    }
}
