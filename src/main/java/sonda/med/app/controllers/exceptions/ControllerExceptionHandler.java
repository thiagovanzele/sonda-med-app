package sonda.med.app.controllers.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import sonda.med.app.model.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		String error = "Erro de validacao";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		Map<String, String> validationErrors = new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach(errorObject -> {
			String fieldName = ((FieldError) errorObject).getField();
			String errorMessage = errorObject.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});

		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setValidationErrors(validationErrors);
		return ResponseEntity.status(status).body(err);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso nao encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
		
	}

}
