/**
 * 
 */
package net.swansonstuff.ifttt;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dan Swanson
 *
 */
@RunWith(Parameterized.class)
public class ValidatorsTests {
		
	Logger log = LoggerFactory.getLogger(ValidatorsTests.class);

	private boolean checkForAuth;
	private String[] rawHeaders;

	private boolean shouldPassCheck;

	public ValidatorsTests(boolean shouldPassCheck, boolean checkForAuth, String[] rawHeaders) {
		this.shouldPassCheck = shouldPassCheck;
		this.checkForAuth = checkForAuth;
		this.rawHeaders = rawHeaders;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {     
			{
				true,
				false,
				new String[] {
						"User-Agent: IFTTT-Protocol/v1",
						"IFTTT-Test-Mode: 1",
						"IFTTT-Channel-Key: test_key",
						"IFTTT-Service-Key: test_key",
						"X-Request-ID: 8fb15b4a6a5048d9bb45a7b8caa7f4a8"
				}

			}, 
			{
				true,
				true,
				new String[] {
						"Host: api.example-service.com",
						"Authorization: Bearer b29a71b4c58c22af116578a6be6402d2",
						"Accept: application/json",
						"IFTTT-Channel-Key: test_key",
						"IFTTT-Service-Key: test_key",
						"Accept-Charset: utf-8",
						"Accept-Encoding: gzip, deflate",
						"Content-Type: application/json",
						"X-Request-ID: 7f7cd9e0d8154531bbf36da8fe24b449"
				}
			},
			{
				false,
				true,
				new String[] {
						"Host: api.example-service.com",
						"Accept: application/json",
						"IFTTT-Channel-Key: test_key",
						"IFTTT-Service-Key: test_key",
						"Accept-Charset: utf-8",
						"Accept-Encoding: gzip, deflate",
						"Content-Type: application/json",
						"X-Request-ID: 7f7cd9e0d8154531bbf36da8fe24b449"
				}
			}

		});
	}

	@Test
	public void optionalUserValidation() {

		IftttConfiguration config = new IftttConfiguration();
		config.setIftttKey("test_key");
		IftttApplication.getInstance().setConfig(config);
		HttpHeaders headers = HeaderGenerator.generate(rawHeaders);
		boolean caughtException = false;
		try {
			Validators.validate(headers, checkForAuth);
		} catch(WebApplicationException wae) {
			log.error("Caught: {}", wae.getMessage());
			caughtException = true;
		}

		if (shouldPassCheck) {
			assertTrue("Validates when user auth "+(checkForAuth ? "is" : "is not")+" required", !caughtException);
		} else {
			assertTrue("Fails when user auth "+(checkForAuth ? "is" : "is not")+" required", caughtException);

		}
	}	

}
