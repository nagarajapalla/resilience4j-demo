package com.demo.resilience_demo.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class CircuitBreakingInterceptor implements ClientHttpRequestInterceptor{

	@Override
	@Retry(name = "retryApi", fallbackMethod = "fallback")
	@CircuitBreaker(name="CircuitBreakerService",fallbackMethod = "fallbackCircuit")
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
        // Proceed with the request if the circuit is closed or half-open
		System.err.println("---- In actual method call -----. time:"+new Date());
        
		try {
            ClientHttpResponse response = execution.execute(request, body);
            // Analyze the response to determine success or failure
            // If the response indicates success, record it with the circuit breaker
            // If the response indicates failure, record it with the circuit breaker
            return response;
        } catch (IOException e) {
            // Record the failure with the circuit breaker
            //circuitBreaker.onError(e); // Example using Resilience4j
            throw e;//new RuntimeException("error from intercept method..."); // Rethrow or return a fallback in case of connection issues
        }
	}
	
    // Fallback method - must have compatible signature and return type
    public  ClientHttpResponse  fallback(Throwable t) {
        System.err.println("FallbackRetry executed due to: "+t.getMessage());
        //return "Fallback response: Service currently unavailable.";
        
        ClientHttpResponse response = new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				String myString = "hello from fallback method";
				// Convert the string to a byte array using UTF-8 encoding
	            byte[] byteArray = myString.getBytes(StandardCharsets.UTF_8);

	            // Create an InputStream from the byte array
	            InputStream inputStream = new ByteArrayInputStream(byteArray);

				
				return inputStream;
			}

			@Override
			public HttpHeaders getHeaders() {
				// TODO Auto-generated method stub
				return new HttpHeaders();
			}

			@Override
			public HttpStatusCode getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatusCode.valueOf(500);
			}

			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return "Status 500";
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
        };
        
        return response;
        
        }
    
    public  ClientHttpResponse  fallbackCircuit(Throwable t) {
        System.err.println("fallbackCircuit executed due to: "+t.getMessage());
        //return "Fallback response: Service currently unavailable.";
        
        ClientHttpResponse response = new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				String myString = "hello from fallback method";
				// Convert the string to a byte array using UTF-8 encoding
	            byte[] byteArray = myString.getBytes(StandardCharsets.UTF_8);

	            // Create an InputStream from the byte array
	            InputStream inputStream = new ByteArrayInputStream(byteArray);

				
				return inputStream;
			}

			@Override
			public HttpHeaders getHeaders() {
				// TODO Auto-generated method stub
				return new HttpHeaders();
			}

			@Override
			public HttpStatusCode getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatusCode.valueOf(500);
			}

			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return "Status 500";
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
        };
        
        return response;
        
        }

    
}
