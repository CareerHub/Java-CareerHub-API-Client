package com.careerhub.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;

public final class RestHttpClient implements AutoCloseable {
	private String resourceLocation;
	private List<Header> headers = null;
	
	private CloseableHttpClient client;
	
	public RestHttpClient(String baseUrl, String location) {
		if(baseUrl == null || baseUrl.isEmpty()) throw new IllegalArgumentException("baseUrl must not be null or empty");
		if(location == null || location.isEmpty()) throw new IllegalArgumentException("location must not be null or empty");
		
		if(!baseUrl.endsWith("/")) {
			baseUrl += "/";
		}
		
		if(location.startsWith("/")) {
			location = location.substring(1);
		}
		
		if(!location.endsWith("/")) {
			location += "/";
		}
		
		this.resourceLocation = baseUrl + location;
		
		client = HttpClients.createDefault();
		
		headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Content-Type", "application/json"));
		headers.add(new BasicHeader("Accept", "application/json"));
	}
	
	public <T> T getResource(String resource, Class<T> type) throws IOException {
		String url = getResourceUrl(resource);
		
		HttpGet request = new HttpGet(url);
		return getResponse(request, type);
	}

	public <T, R> R postResource(String resource, T content, Class<R> type) throws IOException {
		String url = getResourceUrl(resource);
		
		HttpEntity entity = getJSONEntity(content);
		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		
		return getResponse(request, type);
	}
	
	public <T, R> R putResource(String resource, T content, Class<R> type) throws IOException {
		String url = getResourceUrl(resource);
		
		HttpEntity entity = getJSONEntity(content);
		HttpPut request = new HttpPut(url);
		request.setEntity(entity);
		
		return getResponse(request, type);
	}
	
	public void deleteResource(String resource) throws IOException, CareerHubApiException {
		String url = getResourceUrl(resource);
		
		HttpDelete request = new HttpDelete(url);
		
		getRawResponse(request);
	}
	
	@Override
	public void close() throws Exception {
		client.close();
	}
	
	private String getResourceUrl(String resource) {
		if(resource.startsWith("/")) {
			resource = resource.substring(1);
		}
		
		return resourceLocation + resource;
	}
	
	public void addDefaultHeader(String name, String value) {
		this.headers.add(new BasicHeader(name, value));
	}
	
	public void addOAuthHeader(String accessToken) {
		this.addDefaultHeader("Authorization", "Bearer " + accessToken);
	}	

	private Header[] getDefaultHeaders() {
		return headers.toArray(new Header[headers.size()]);
	}
	
	private <T> T getResponse(HttpRequestBase request, Class<T> type) throws IOException {
		request.setHeaders(getDefaultHeaders());		
		
		ResponseHandler<T> handler = getResponseHandler(type);
		return client.execute(request, handler);
	}
	
	private String getRawResponse(HttpRequestBase request) throws IOException, CareerHubApiException {
		request.setHeaders(getDefaultHeaders());
		
		try(CloseableHttpResponse response = client.execute(request)) {
			StatusLine statusLine = response.getStatusLine();
			
			if(statusLine.getStatusCode() != 200) {
	            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
			}
			
			HttpEntity entity = response.getEntity();
			
			String raw = EntityUtils.toString(entity);

			return raw;
		}
	}
	
	private <T> HttpEntity getJSONEntity(T content) throws JsonGenerationException, JsonMappingException, IOException {
		try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			try(Writer writer = new OutputStreamWriter(out, "UTF-8")) {
	
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(writer, content);
				
				HttpEntity entity = new ByteArrayEntity(out.toByteArray());
				
				return entity;
			}
		}
	}
	
	private <T> ResponseHandler<T> getResponseHandler(final Class<T> type) {
		return new ResponseHandler<T>() {

			@Override
			public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				 
		        if (statusLine.getStatusCode() != 200) {
		            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		        }
		        
		        HttpEntity entity = response.getEntity();
		        
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }

		        ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        
		        try(Reader reader = new InputStreamReader(entity.getContent(), charset)) {				        

					ObjectMapper mapper = new ObjectMapper();
					return mapper.readValue(reader, type);
		        }
			}
		};
		
	}
}
