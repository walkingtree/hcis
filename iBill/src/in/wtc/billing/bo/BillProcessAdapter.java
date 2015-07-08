/**
 * 
 */
package in.wtc.billing.bo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.wtc.hcis.billing.da.BillProcessRouting;
import com.wtc.hcis.billing.da.BillRegister;
import com.wtc.hcis.billing.da.extn.BillRegisterDAOExtn;

import in.wtc.billing.bm.AccountBillData;
import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.constants.BillingConstants;
import in.wtc.billing.exception.BillException;
import in.wtc.billing.exception.Fault;

/**
 * @author Alok Ranjan
 *
 */
public class BillProcessAdapter {

	private BillRegisterDAOExtn billRegisterDAO;
	
	public BillProcessAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	private BillRegister getBillRegister( String processName ) {
		BillRegister billRegister = billRegisterDAO.findById( processName );
	       
	    if ( billRegister == null ) {
	    	   Fault fault = new Fault( BillingConstants.PROCESS_NOT_REGISTERED );
			   throw new BillException( fault.getFaultMessage() 
						                    + " PROCESS_NAME = " 
						                    + processName, 
										 fault.getFaultCode(),
										 fault.getFaultType()  );
	    } else {
	    	return billRegister;
	    }
	}
	
	public BillDataBM runBillingForProcess( BillProcessRouting billProcessRouting, 
			                                String referenceNbr,
			                                String acAccountId,
			                                Long billNumber ) {
		BillDataBM billDataBM = null;
		
		String implClassName = billProcessRouting.getOverrideImplClassName();
		
		if ( implClassName == null || implClassName.length() < 1 )  {
			BillRegister billRegister = this.getBillRegister( billProcessRouting.getId().getProcessName() );
			implClassName = billRegister.getImplClassName();
		}
		
	    
	    try {

	    	Class cls = Class.forName( implClassName ); 
	    	
			Constructor ct = cls.getConstructor();
			Object arglist[] = null;
			Object billProcess = ct.newInstance( arglist );
	    	
//	    	String beanName[] = ctx.getBeanNamesForType(cls);
//	    	Object billProcess = ctx.getBean(beanName[0]);
			
			Class partypes[] = new Class[4];
            partypes[0] = String.class;
            partypes[1] = String.class;
            partypes[2] = String.class;
            partypes[3] = Long.class;
            Method meth = cls.getMethod( "runBilling", partypes );
           
            Object mArglist[] = new Object[4];
            mArglist[0] = acAccountId;
            mArglist[1] = billProcessRouting.getId().getClientName();
            mArglist[2] = referenceNbr;
            mArglist[3] = billNumber;
            Object retobj = meth.invoke( billProcess, mArglist);
            
            billDataBM = (BillDataBM)retobj;
		} catch (Exception e) {
			 Fault fault = new Fault( BillingConstants.PROCESS_INSTANTIATION_FAILED );
			   throw new BillException( fault.getFaultMessage() 
						                    + " PROCESS_NAME = " 
						                    + billProcessRouting.getId().getProcessName()
						                    + " and CLIENT_NAME = "
						                    + billProcessRouting.getId().getClientName(), 
										 fault.getFaultCode(),
										 fault.getFaultType()  );
		}
		
		return billDataBM;
	}
	
	AccountBillData applyCreditResiduals( String clientName, String accountId,Long billNumber, boolean evaluateDeposits ) {
		
		BillRegister billRegister = this.getBillRegister( BillingConstants.PROCESS_ACCOUNTING );
		try {
			Class cls = Class.forName( billRegister.getImplClassName() );   
			Constructor ct = cls.getConstructor();
			Object arglist[] = null;
			Object billProcess = ct.newInstance(arglist);
			
//			String beanName[] = ctx.getBeanNamesForType(cls);
//			Object billProcess = ctx.getBean(beanName[0]);
			
			Class partypes[] = new Class[4];
            partypes[0] = String.class;
            partypes[1] = String.class;
            partypes[2] = Long.class;
            partypes[3] = Boolean.TYPE;
            Method meth = cls.getMethod( "applyCreditResiduals", partypes );
           
            Object mArglist[] = new Object[4];
            mArglist[0] = clientName;
            mArglist[1] = accountId;
            mArglist[2] = billNumber;
            mArglist[3] = evaluateDeposits;
            Object retobj = meth.invoke( billProcess, mArglist);
            
            return (AccountBillData)retobj;
		} catch (Exception e) {
			 Fault fault = new Fault( BillingConstants.PROCESS_INSTANTIATION_FAILED );
			   throw new BillException( fault,e  );
		}
	}
	
	double createReceivables( double totalChargeAmt, 
			                  Long   billNumber, 
			                  String memo,
			                  String clientName,
			                  String accountId ) {
		BillRegister billRegister = this.getBillRegister( BillingConstants.PROCESS_ACCOUNTING );
		try {
			Class cls = Class.forName( billRegister.getImplClassName() );   
			
			Constructor ct = cls.getConstructor();
			Object arglist[] = null;
			Object billProcess = ct.newInstance(arglist);
			
//			String beanName[] = ctx.getBeanNamesForType(cls);
//			Object billProcess = ctx.getBean(beanName[0]);
			
			Class partypes[] = new Class[5];
            partypes[0] = Double.TYPE;
            partypes[1] = Long.class;
            partypes[2] = String.class;
            partypes[3] = String.class;
            partypes[4] = String.class;
            Method meth = cls.getMethod( "createReceivables", partypes );
           
            Object mArglist[] = new Object[5];
            mArglist[0] = totalChargeAmt;
            mArglist[1] = billNumber;
            mArglist[2] = memo;
            mArglist[3] = clientName;
            mArglist[4] = accountId;
            Object retobj = meth.invoke( billProcess, mArglist);
            
            return (Double)retobj;
		} catch (Exception e) {
			 Fault fault = new Fault( BillingConstants.PROCESS_INSTANTIATION_FAILED );
			   throw new BillException( fault.getFaultMessage(), 
										 fault.getFaultCode(),
										 fault.getFaultType()  );
		}
	}

	public Integer createInvoice( String accountId,Double amount, String description){

		BillRegister billRegister = this.getBillRegister( BillingConstants.PROCESS_ACCOUNTING );
		try {
			Class cls = Class.forName( billRegister.getImplClassName() );   
			
			Constructor ct = cls.getConstructor();
			Object arglist[] = null;
			Object billProcess = ct.newInstance(arglist);
			
//			String beanName[] = ctx.getBeanNamesForType(cls);
//			Object billProcess = ctx.getBean(beanName[0]);
			
			Class partypes[] = new Class[3];
            partypes[0] = String.class;
            partypes[1] = Double.class;
            partypes[2] = String.class;
            Method meth = cls.getMethod( "createInvoice", partypes );
           
            Object mArglist[] = new Object[3];
            mArglist[0] = accountId;
            mArglist[1] = amount;
            mArglist[2] = description;
            Object retobj = meth.invoke( billProcess, mArglist);
            
            return (Integer)retobj;
		} catch (Exception e) {
			 Fault fault = new Fault( BillingConstants.PROCESS_INSTANTIATION_FAILED );
			   throw new BillException( fault.getFaultMessage(), 
										 fault.getFaultCode(),
										 fault.getFaultType()  );
		}
		
	}
	public void setBillRegisterDAO(BillRegisterDAOExtn billRegisterDAO) {
		this.billRegisterDAO = billRegisterDAO;
	}

}
