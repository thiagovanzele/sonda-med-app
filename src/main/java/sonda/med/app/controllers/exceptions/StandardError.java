package sonda.med.app.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;
	private Map<String, String> validationErrors;

	public StandardError() {
	}

	public StandardError(Instant timestamp, Integer status, String error, String path, Map<String, String> validationErrors) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
		this.validationErrors = validationErrors;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	

}
