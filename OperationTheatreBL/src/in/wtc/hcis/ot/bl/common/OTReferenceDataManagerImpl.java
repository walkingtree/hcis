package in.wtc.hcis.ot.bl.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.List;

import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.extn.OtDetailDAOExtn;


public class OTReferenceDataManagerImpl implements OTReferenceDataManager {
	
	OtDetailDAOExtn otDetailDAO;
	
	public ListRange getOperationTheatreList(int start, int count, String orderBy) throws HCISException 
	{ 
		ListRange listRange = new ListRange();
		try {
			List<OtDetail> otList =otDetailDAO.findAll();
		    Object[] codeDescObj = new Object[otList.size()];
		    if(otList!=null && otList.size()>0) {
		    	for(int i =0; i<otList.size();i++) {
		    		OtDetail otDetail = otList.get(i);
		    		CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(otDetail.getOtId()); 
			    	codeAndDescription.setDescription(otDetail.getName());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(otList.size());
	   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	public void setOtDetailDAO(OtDetailDAOExtn otDetailDAO) {
		this.otDetailDAO = otDetailDAO;
	}
	

}
