package exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String mensagem) {
		super(mensagem);
	}
	
	
	
}
