/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.ListRange;

import java.util.List;

/**
 * @author Bhavesh
 *
 */
public class LimsUtils {

	/**
	 * Checks whether 'Code' field of given CodeAndDescription is valid or not.
	 * @param codeAndDescription
	 * @return
	 */
	public static boolean isValid( CodeAndDescription codeAndDescription){
		
		if( codeAndDescription != null && codeAndDescription.getCode()!= null
				&& !codeAndDescription.getCode().isEmpty()){
			
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param listObject--> List object to be converted into ListRange
	 * @param totalSize --> Option, total size of listRange object, if it is more than the size of given listObject 
	 * @return
	 */
	public static ListRange convertListToListRange(List listObject, Integer totalSize){
		
		ListRange listRange = new ListRange();
		if( listObject != null && !listObject.isEmpty() ){
			
			Object[] objects = listObject.toArray();
			listRange.setData( objects );
			if(totalSize != null && totalSize > listObject.size()){
				
				listRange.setTotalSize( totalSize );
			}else{
				
				listRange.setTotalSize(listObject.size());
			}
			
		}else{
			
			listRange.setTotalSize(0);
			listRange.setData( new Object[0]);
		}
		
		return listRange;
	}



}
