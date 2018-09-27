/**
 * 
 */
package net.swansonstuff.ifttt;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Dan Swanson
 *
 */
public class Validators {

	private final static Logger log = LoggerFactory.getLogger(Validators.class);
	private static IftttApplication app = IftttApplication.getInstance();

	public static IftttApplication getApp() {
		return app;
	}
	
	public static void setApp(IftttApplication appInstance) {
		app = appInstance;
	}

	public static final String HEADER_TEST_MODE = "IFTTT-Test-Mode"; // : 1
	public static final String HEADER_CHANNEL_KEY = "IFTTT-Channel-Key"; // : HKhnyC_yF47nFsINyldkoGwHBipRtT1fXcya8DiFiMQkP_YCYo67c_HKLVE56cJn
	public static final String HEADER_SERVICE_KEY = "IFTTT-Service-Key"; // : HKhnyC_yF47nFsINyldkoGwHBipRtT1fXcya8DiFiMQkP_YCYo67c_HKLVE56cJn
	public static final String HEADER_REQUEST_ID = "X-Request-ID"; // : 178f7845740d4d6db55f18aee47d3b28
	public static final String HEADER_AUTHORIZATION = "Authorization"; // : Bearer b29a71b4c58c22af116578a6be6402d2

	public static void validate(HttpHeaders headers, boolean authRequired) {
		List<String> channelKeys = headers.getRequestHeader(HEADER_CHANNEL_KEY);
		if (channelKeys == null || channelKeys.size() < 1) {
			log.error("Missing {}", HEADER_CHANNEL_KEY);
			throw new WebApplicationException("Missing "+HEADER_CHANNEL_KEY);
		}

		List<String> serviceKeys = headers.getRequestHeader(HEADER_SERVICE_KEY);
		if (serviceKeys == null) {
			log.error("Missing {}", HEADER_SERVICE_KEY);
			throw new WebApplicationException("Missing "+HEADER_SERVICE_KEY);
		} else if (!serviceKeys.contains(app.getConfig().getIftttKey())){
			log.error("Invalid {}", HEADER_SERVICE_KEY);
			throw new WebApplicationException("Invalid "+HEADER_SERVICE_KEY);            
		}

		if (authRequired) {
			List<String> bearers = headers.getRequestHeader(HEADER_AUTHORIZATION); 
			if (bearers == null || bearers.isEmpty()) {
				log.error("Missing {}", HEADER_AUTHORIZATION);
				throw new WebApplicationException("Missig "+HEADER_AUTHORIZATION);
			} else {
				// do request validation here
			}
		}

	}

}
