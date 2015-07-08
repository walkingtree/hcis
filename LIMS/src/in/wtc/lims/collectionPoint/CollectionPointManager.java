/**
 * 
 */
package in.wtc.lims.collectionPoint;

import java.util.List;

import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.SpecimenCollectionPointBM;
import in.wtc.lims.exception.LimsException;

/**
 * @author Bhavesh
 *
 *	Specimen collection points are the centers for collecting
 *	specimen/sample from patient for desired Laboratory test.
 *
 *	Each collection point can be associated with one or more Laboratory. 
 */
public interface CollectionPointManager {

	/**
	 * Sets up new specimen collection point information into the system.
	 * Also save association with Lab , if any.
	 * One collection point can be associated with one or more Laboratory.  
	 * @param collectionPointBM
	 * @throws LimsException
	 */
	public void addCollectionPoint(SpecimenCollectionPointBM collectionPointBM) throws LimsException;
	
	/**
	 * Modifies collection point information including contact detail information.
	 * Association with laboratory also can be added or removed. 
	 * 
	 * @param collectionPointBM
	 * @return
	 * @throws LimsException
	 */
	SpecimenCollectionPointBM modifyCollectionPoint(SpecimenCollectionPointBM collectionPointBM ) throws LimsException;
	
	
	ListRange getCollectionPoint( String collectionPointName,
								 String collectionPointId,
								 String associatedLabId,
								 String areaCovered,
								 String city,
								 int start,
								 int count,
								 String orderBy) throws LimsException;
	/**
	 * This method takes collectionPointId as input it will retrun SpecimenCollectionPointBM
	 * Whenever anyone press the edit button in Collection point UI immediately this method will be called
	 * and populate this data into the UI.(In the case of edit).  
	 * @param collectionPointId
	 * @return
	 * @throws LimsException
	 */
	
	SpecimenCollectionPointBM getCollectionPointForId( String collectionPointId)
	throws LimsException;
	
}
