package com.demo.resilience_demo.config;

import java.io.IOException;

import org.springframework.http.HttpRequest;

public class ExternalCallException extends IOException {
	
	private Throwable exception;
	private HttpRequest request;

	public ExternalCallException(Throwable e, HttpRequest request) {
		this.exception = e;
		this.request = request;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

}
