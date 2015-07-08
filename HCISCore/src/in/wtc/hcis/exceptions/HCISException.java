/**
 * 
 */
package in.wtc.hcis.exceptions;

/**
 * @author Rohit
 *
 */
public class HCISException extends RuntimeException {
	public HCISException() {
		// TODO Auto-generated constructor stub
	}
	
	private static final long serialVersionUID = 3119120920769104770L;
	private String errorCode;
    private String exceptionType;
    private String message;
    
	public HCISException( final String message, final String errorCode, final String exceptionType ) {
		super();
    	this.message = message;
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
	}
	
	public HCISException( final Fault fault) {
		super();
		this.errorCode = fault.getFaultCode();
		this.message = fault.getFaultMessage();
		this.exceptionType = fault.getFaultType();
	}

	public HCISException( final Fault fault, Exception ex) {
		super();
		this.errorCode = fault.getFaultCode();
		this.message = fault.getFaultMessage() + " : " + ex.getMessage();
		this.exceptionType = fault.getFaultType();
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the exceptionID
	 */
	public String getExceptionType() {
		return exceptionType;
	}

	/**
	 * @param exceptionID the exceptionID to set
	 */
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
