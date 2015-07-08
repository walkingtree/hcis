/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class TransferBM implements Serializable{
	
	private Integer admissionNbr;
	private Integer doctorOrderNbr;
	private CodeAndDescription dischargeType;
	private CodeAndDescription doctorOrderStatus;
	private Date orderCreationDtm;
	private String orderRequestedBy;
	private String approvedBy;
	private Date approvalTime;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Date expectedDischargeDate;
	private Integer expectedDischargeTime;
	private Date actualDischargeTime;
	private List<DoctorOrderDetailsBM>doctorOrderDetailsList;
	private String orderDesc;
	private String orderDictation;
	private Integer patientId;
	private Integer doctorId;

}
