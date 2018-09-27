package net.swansonstuff.ifttt;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.message.internal.HeaderUtils;

public class HeaderGenerator {

	public static HttpHeaders generate(String[] rawHeaders) {

		return new HttpHeaders() {

			MultivaluedMap< String, String> headers = HeaderUtils.createInbound();
			{
				String key;
				String value;
				for (String rawHeader : rawHeaders) {
					String[] parts = rawHeader.split(":");
					if (parts.length == 2 && parts[0] != null && parts[1] != null) {
						key = parts[0].trim();
						value = parts[1].trim();
						// System.out.println("adding "+key+"="+value);
						headers.add(key, value);
					}
				}

			}

			@Override
			public MultivaluedMap<String, String> getRequestHeaders() {
				return null;
			}

			@Override
			public List<String> getRequestHeader(String name) {
				return headers.get(name);
			}

			@Override
			public MediaType getMediaType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getLength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Locale getLanguage() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getHeaderString(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Date getDate() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Cookie> getCookies() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<MediaType> getAcceptableMediaTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Locale> getAcceptableLanguages() {
				// TODO Auto-generated method stub
				return null;
			}
		};

	}
}