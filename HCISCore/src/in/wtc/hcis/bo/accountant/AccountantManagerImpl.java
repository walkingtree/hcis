/**
 * 
 */
package in.wtc.hcis.bo.accountant;

import in.wtc.hcis.bm.base.ReceivableBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.bo.patient.PatientManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Bhavesh
 *
 */
public class AccountantManagerImpl implements AccountantManager {

	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	
	public Integer createReceipt(Integer patientId, Double amount,
			Map<String, String> attributeMap, String createdBy,String invoiceId) {

		
		Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(patientId,
											RegistrationConstants.REFERENCE_TYPE_PAT);
		
		return  eagleIntegration.createReceipt(businessPartnerId, amount, attributeMap, createdBy,invoiceId, null);
	}

	public ListRange getPatinetReceivable(Integer patientId,int start,int count,String orderBy) {
		
		Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(patientId,
				RegistrationConstants.REFERENCE_TYPE_PAT);
		
		List<ReceivableBM> receivableBMList = eagleIntegration.getReceivables(businessPartnerId);
		
		
		
		ListRange listRange = new ListRange();
		
		List<ReceivableBM> pageSizeData = new ArrayList<ReceivableBM>();
		int index = 0;
		if (receivableBMList != null && receivableBMList.size() > 0) {

			while( (start+index < start + count) && (receivableBMList.size() > start+index) ){
			
			pageSizeData.add( receivableBMList.get(start+index) );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(receivableBMList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;

		
	}

}
