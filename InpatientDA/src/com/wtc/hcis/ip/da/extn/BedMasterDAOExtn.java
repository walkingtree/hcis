/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedMasterDAO;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedReservationSpecialFeatures;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.NursingUnit;

/**
 * @author Alok Ranjan
 *
 */
public class BedMasterDAOExtn extends BedMasterDAO {

	public static final String ORDER_BY_FLOOR = "FLOOR";
	public static final String ORDER_BY_UNIT = "UNIT";
	public static final String ORDER_BY_BED_TYPE = "BED_TYPE";
	public static final String BED_STATUS_AVAILABLE = "AVAILABLE";
	public static final String BED_STATUS_OCCUPIED = "OCCUPIED";
	
	public static final String UNIT_TYPE_CD = "nursingUnit.nursingUnitType.unitTypeCd";
	public static final String BED_TYPE = "bedType.bedTypeCd";
	public static final String NURSING_UNIT_NAME = "nursingUnit.unitName";
	public static final String BED_STATUS_CD = "bedStatus.bedStatusCd";
	public static final String ADMISSION_NUMBER = "admission.admissionReqNbr";
	public static final String BED_NUMBER = "bedNumber";
	public static final String PATIENT_TRANSFER_DISCHARGE_DATE = "expectedDischargeDtm";
	
	public static final String UNIT_NAME = "nursingUnit.unitName";
	
	public List<BedMaster> getOccupiedBeds( String unitTypeCd, 
			 								Date reservationFromDtm, 
			                                Date reservationToDtm ) {
		
//		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
//		bedCriteria.createAlias( "nursingUnit", "nursingUnit" );
//		bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.UNIT_TYPE_CD , unitTypeCd ) )
//		           .add(Restrictions.le(propertyName, value));
//		
//		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );	
//		return bedMasterList;
		// TODO : We need to have admission date and expected discharge date on Bed Master.
		return null;
	}
	
	public List<BedMaster>getAllBeds( String orderByColumn) {
		
		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
		
		if ( orderByColumn.equals( BedMasterDAOExtn.ORDER_BY_FLOOR ) ) {
			bedCriteria.addOrder( Order.asc( BedMasterDAOExtn.FLOOR_NBR ) );
			
		} else if ( orderByColumn.equals( BedMasterDAOExtn.ORDER_BY_UNIT ) ) {
			bedCriteria.addOrder( Order.asc( BedMasterDAOExtn.UNIT_NAME ) );
			
		}
		
		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );	
		return bedMasterList;
	}
	
	public List<BedMaster>getAvailableBeds( String nursingUnitTypeCd, String nursingUnitName ) {
		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
		
		if ( nursingUnitTypeCd != null && nursingUnitTypeCd.length() > 0 ) {
			bedCriteria.createAlias("nursingUnit", "nursingUnit" );
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.UNIT_TYPE_CD , nursingUnitTypeCd));
			
			 if ( nursingUnitName != null && nursingUnitName.length() > 0 ) {
				 bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.NURSING_UNIT_NAME , nursingUnitName ) );
			 }
			 
		} else if ( nursingUnitName != null && nursingUnitName.length() > 0 ) {
			bedCriteria.createAlias("nursingUnit", "nursingUnit" );
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.NURSING_UNIT_NAME , nursingUnitName ) );
		}
		
		
		bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.BED_STATUS_CD, BedMasterDAOExtn.BED_STATUS_AVAILABLE ) );
		
		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );	
		return bedMasterList;
	}
	
	public List<BedMaster>getAllAvailableBeds( ) {
		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
		bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.BED_STATUS_CD, BedMasterDAOExtn.BED_STATUS_AVAILABLE ) );
		
		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );	
		return bedMasterList;
	}
	
	public List<BedMaster> getCurrentlyOccupiedBedBeds( Integer admissionNumber, Integer patientId ) {
		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
		
		if ( admissionNumber != null ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.ADMISSION_NUMBER, admissionNumber ) );
		}
		
		if ( patientId != null ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.PATIENT_ID, patientId ) );
		}
		
		bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.BED_STATUS_CD, BedMasterDAOExtn.BED_STATUS_OCCUPIED ) );
//		bedCriteria.add( Restrictions.isNull( BedMasterDAOExtn.TRANSFER_DISCHARGE_IND ) ); field is removed
		
		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );
		
		if ( bedMasterList != null && !bedMasterList.isEmpty() ) {
			return bedMasterList;
		} else {
			return null;
		}
		
	}
	
	public List<BedMaster> getBedMaster( String bedNumber, 
										 String roomNumber,
										 String floorNumber, 
										 String nursingUnitName, 
										 String nursingUnitTypeCd,
										 Integer patientId,
										 Integer patientAdmissionNumber,
										 String bedStatusCode, 
										 Date fromTransferDischargeDate, 
										 Date toTransferDischargeDate,
										 String orderByColumns, 
										 String sortDir ) {
		
		DetachedCriteria bedCriteria = DetachedCriteria.forClass( BedMaster.class );
		
		if ( bedNumber != null && bedNumber.length() > 0 ) {
			bedCriteria.add( Restrictions.ilike( BedMasterDAOExtn.BED_NUMBER, "%" + bedNumber + "%") );
		}
		
		if ( roomNumber != null && roomNumber.length() > 0 ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.ROOM_NBR, roomNumber ) );
		}
		
		if ( floorNumber != null && floorNumber.length() > 0 ) {
			bedCriteria.add( Restrictions.ilike( BedMasterDAOExtn.FLOOR_NBR, "%" + floorNumber + "%") );
		}
		
		if ( nursingUnitName != null && nursingUnitName.length() > 0 ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.NURSING_UNIT_NAME, nursingUnitName ) );
		}
		if ( nursingUnitTypeCd != null && nursingUnitTypeCd.length() > 0 ) {
			bedCriteria.createAlias("nursingUnit", "nursingUnit" );
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.UNIT_TYPE_CD , nursingUnitTypeCd));
		}
		
		if ( patientId != null ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.PATIENT_ID, patientId) );
		}
		
		if ( patientAdmissionNumber != null ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.ADMISSION_NUMBER, patientAdmissionNumber ) );
		}
		
		if ( bedStatusCode != null && bedStatusCode.length() > 0 ) {
			bedCriteria.add( Restrictions.eq( BedMasterDAOExtn.BED_STATUS_CD, bedStatusCode ) );
		}
		
		if ( fromTransferDischargeDate != null ) {
			
			if ( toTransferDischargeDate != null ) {
				bedCriteria.add( Restrictions.and(Restrictions.isNotNull(BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE), Restrictions.between( BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE, fromTransferDischargeDate, toTransferDischargeDate )) );
			} else {
				bedCriteria.add( Restrictions.or(Restrictions.isNotNull(BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE), Restrictions.ge( BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE, fromTransferDischargeDate ) ));
			}
			
		} else if ( toTransferDischargeDate != null ) {
			bedCriteria.add( Restrictions.or(Restrictions.isNotNull(BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE),Restrictions.le( BedMasterDAOExtn.PATIENT_TRANSFER_DISCHARGE_DATE, toTransferDischargeDate )) );
		}
		
		if (orderByColumns != null && orderByColumns.length() > 0) {
			String[] columnArr = orderByColumns.split(",");
	
			for(int i = 0; i < columnArr.length; i++) {
				if (columnArr[i].equals(BedMasterDAOExtn.ORDER_BY_UNIT))
					bedCriteria.addOrder(Order.asc(BedMasterDAOExtn.NURSING_UNIT_NAME));
				if (columnArr[i].equals(BedMasterDAOExtn.ORDER_BY_BED_TYPE))
					bedCriteria.addOrder(Order.asc(BedMasterDAOExtn.BED_TYPE));
				if (columnArr[i].equals(BedMasterDAOExtn.ORDER_BY_FLOOR))
					bedCriteria.addOrder(Order.asc(BedMasterDAOExtn.FLOOR_NBR));
			}
			
			bedCriteria.addOrder(Order.asc(BedMasterDAOExtn.BED_NUMBER));
		}
		
		List<BedMaster>bedMasterList = getHibernateTemplate().findByCriteria( bedCriteria );
		
		if ( bedMasterList != null && !bedMasterList.isEmpty() ) {
			return bedMasterList;
		} else {
			return null;
		}
	}
	
	
}
