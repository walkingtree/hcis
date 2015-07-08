package in.wtc.lims.labDetail;


import java.util.List;

import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.LabDetailBM;
import in.wtc.lims.exception.LimsException;


public interface LabDetailManager {


	void addLabDetail( LabDetailBM labDetailBM) throws LimsException;
		
		LabDetailBM modifyLabDetail(LabDetailBM modifiedLabDetailBM ) throws LimsException;
		
		ListRange getLabDetail(String labId, String hospitalName, String labType,String labName,String businessName,String branchName,String labOperatorID, String directionFromKnownPlace,int start,int limit, String orderBy ) throws LimsException;
		
		boolean removeLabDetail(String[] labId) throws LimsException;
	}
