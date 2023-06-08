package contact_management.exception;

public class RoleAlreadyExistsException extends Exception{
    public RoleAlreadyExistsException(){
        super("role already exists!");
    }

}
