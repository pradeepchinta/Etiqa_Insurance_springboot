package my.com.etiqa.maybank.exception;

/**
 * 
 * @author Pradeep Chinta
 * custom exception
 *
 */
public class DuplicateEntryException extends Exception {

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 * constructor to accept exception message
	 */
	public DuplicateEntryException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param e
	 * constructor to accept exception
	 */
	public DuplicateEntryException(Exception e){
		super(e);
	}
}
