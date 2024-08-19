package sonda.med.app.controllers.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
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
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<StandardError> validationException(ValidationException e, HttpServletRequest request) {
		String error = "Erro de validacao";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> erroBadCredentials(BadCredentialsException e, HttpServletRequest request) {
		String error = "Credênciais inválidas";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<StandardError> authenticationException(AuthenticationException e, HttpServletRequest request) {
		String error = "Falha na autenticacao";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(JWTVerificationException.class)
	public ResponseEntity<StandardError> tokenValidationException(JWTVerificationException e, HttpServletRequest request) {
		String error = "Falha na autenticacao";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(JWTDecodeException.class)
	public ResponseEntity<StandardError> tokenValidationException(JWTDecodeException e, HttpServletRequest request) {
		String error = "Token invalido";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> tokenValidationException(AccessDeniedException e, HttpServletRequest request) {
		String error = "Acesso negado";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
		err.setMensagem(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

}
