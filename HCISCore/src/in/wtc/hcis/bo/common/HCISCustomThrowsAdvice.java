package in.wtc.hcis.bo.common;

import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class HCISCustomThrowsAdvice implements ThrowsAdvice {


	public void afterThrowing(Method method, Object[] args,	Object target, Exception ex) throws Throwable {

		ex.printStackTrace();

		if( ex instanceof HCISException){

			//Pass the exception to Presentation layer
			throw ex;
		
		}else if( ex instanceof org.springframework.dao.DataIntegrityViolationException ){
			
			//Modify it to Database error
			//
			Fault fault = new Fault(ApplicationErrors.DATABASE_ERROR);
			throw new HCISException(fault.getFaultMessage()+ getFaultMessage(method)+":"+ ex.getMessage(),
					fault.getFaultCode(),ex.getMessage());
			
		}else{//we can have further exception type checking
			
			Fault fault = new Fault(ApplicationErrors.ERROR_ENCOUNTERED);
			throw new HCISException(fault,ex);
		}
    }
	
	private String getFaultMessage( Method method ){
		String methodName = method.getName();
		
		StringBuilder faultName = new StringBuilder(" Failed to ");
		
		for( int index = 0 ; index < methodName.length(); index ++ ){
			
			char tempCahr = methodName.charAt(index);
			if(Character.isUpperCase(tempCahr)){
				faultName.append(" ");
			}
			faultName.append(tempCahr);
		}
		
		return faultName.toString();
	}
		
}

