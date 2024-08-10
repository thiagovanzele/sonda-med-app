package sonda.med.app.model.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Long id, Class<?> classe) {
		super("Resource not found: " + classe.getSimpleName() + " WITH ID: " + id);
	}

}
