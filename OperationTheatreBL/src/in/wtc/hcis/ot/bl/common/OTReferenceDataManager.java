package in.wtc.hcis.ot.bl.common;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

public interface OTReferenceDataManager {
	 ListRange getOperationTheatreList(int start, int count, String orderBy) throws HCISException ;
}
