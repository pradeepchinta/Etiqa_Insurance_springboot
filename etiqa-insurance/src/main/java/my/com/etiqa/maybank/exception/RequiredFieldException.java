package my.com.etiqa.maybank.exception;

/**
 * 
 * @author Pradeep Chinta
 * custom exception
 *
 */
public class RequiredFieldException extends Exception {

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 * constructor to accept exception message
	 */
	public RequiredFieldException(String message){
		super(message);
	}
}
