package pe.com.yourclothes.backend.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException()
    {
        super();
    }
    public ResourceNotFoundException(String message)
    {
        super(message);
    }

}
