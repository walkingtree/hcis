/**
 * 
 */
package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.DoctorDetailBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;
import in.wtc.hcis.bm.base.DoctorSummaryBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.ui.model.DoctorDetails;
import in.wtc.ui.model.DoctorSummary;

import in.wtc.ui.utils.Constants;
import in.wtc.ui.utils.Converters;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vinay Kurudi
 *
 */
public class DoctorManagementController {
	
	DoctorManager doctorManager;
	
	
public void saveDoctorDetails(DoctorDetailBM doctorDetails,
			                      DoctorEspecialtyBM doctorEspectialities,
			                      ContactDetailsBM currentContactDetails,
			                      ContactDetailsBM permanentContactDetails,
			                      ContactDetailsBM emergencyContactDetails) {

		doctorDetails.setBloodGroup(Converters.getEntityValue(doctorDetails.getBloodGroup()));
		doctorDetails.setDoctor(Converters.getEntityValue(doctorDetails.getDoctor()));
		doctorDetails.setGender(Converters.getEntityValue(doctorDetails.getGender()));
		doctorDetails.setIdProof(Converters.getEntityValue(doctorDetails.getIdProof()));
		doctorDetails.setMarital(Converters.getEntityValue(doctorDetails.getMarital()));
		doctorDetails.setReligion(Converters.getEntityValue(doctorDetails.getReligion()));
		doctorDetails.setSaluation(Converters.getEntityValue(doctorDetails.getSaluation()));
		doctorDetails.setNationality(Converters.getEntityValue(doctorDetails.getNationality()));
		
		doctorEspectialities.setDepartmentCode(Converters.getEntityValue(doctorEspectialities.getDepartmentCode()));
		doctorEspectialities.setEspecialtyCode(Converters.getEntityValue(doctorEspectialities.getEspecialtyCode()));
		doctorEspectialities.setRoomCode(Converters.getEntityValue(doctorEspectialities.getRoomCode()));
		
		List<ContactDetailsBM> contactList = new ArrayList<ContactDetailsBM>();
		if(currentContactDetails.getFirstName()!=null && !currentContactDetails.getFirstName().equals("")) {
		currentContactDetails.setContactType(new CodeAndDescription(Constants.CURRENT,Constants.CURRENT));
		currentContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
		
		currentContactDetails.setGender(Converters.getEntityValue(currentContactDetails.getGender()));
		currentContactDetails.setRelationCode(Converters.getEntityValue(currentContactDetails.getRelationCode()));
		currentContactDetails.setSalutation(Converters.getEntityValue(currentContactDetails.getSalutation()));
		currentContactDetails.setState(Converters.getEntityValue(currentContactDetails.getState()));
		currentContactDetails.setCountry(Converters.getEntityValue(currentContactDetails.getCountry()));
		
		contactList.add(currentContactDetails);
		}else {
			currentContactDetails = null;
		}
		
		if(permanentContactDetails.getFirstName()!= null && !permanentContactDetails.getFirstName().equals("")) {
			permanentContactDetails.setContactType(new CodeAndDescription(Constants.PERMANENT,Constants.PERMANENT));
			permanentContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
			
			permanentContactDetails.setCountry(Converters.getEntityValue(permanentContactDetails.getCountry()));
			permanentContactDetails.setGender(Converters.getEntityValue(permanentContactDetails.getGender()));
			permanentContactDetails.setRelationCode(Converters.getEntityValue(permanentContactDetails.getRelationCode()));
			permanentContactDetails.setSalutation(Converters.getEntityValue(permanentContactDetails.getSalutation()));
			permanentContactDetails.setState(Converters.getEntityValue(permanentContactDetails.getState()));
			
			contactList.add(permanentContactDetails);
			
		}else {
			permanentContactDetails = null;
		}
		
		if(emergencyContactDetails.getFirstName()!=null && !emergencyContactDetails.getFirstName().equals("")) {
			emergencyContactDetails.setContactType(new CodeAndDescription(Constants.EMERGENCY_PRIMARY,Constants.EMERGENCY_PRIMARY));
			emergencyContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
			contactList.add(emergencyContactDetails);
			
			emergencyContactDetails.setCountry(Converters.getEntityValue(emergencyContactDetails.getCountry()));
			emergencyContactDetails.setGender(Converters.getEntityValue(emergencyContactDetails.getGender()));
			emergencyContactDetails.setRelationCode(Converters.getEntityValue(emergencyContactDetails.getRelationCode()));
			emergencyContactDetails.setSalutation(Converters.getEntityValue(emergencyContactDetails.getSalutation()));
			emergencyContactDetails.setState(Converters.getEntityValue(emergencyContactDetails.getState()));
			
		} else {
			emergencyContactDetails = null;
		}
		List<DoctorEspecialtyBM> doctorEspecialityList = new ArrayList<DoctorEspecialtyBM>();
		doctorEspecialityList.add(doctorEspectialities);
		DoctorBM doctorBM = new DoctorBM();
		doctorBM.setActive(true);
		doctorBM.setDoctorDetail(doctorDetails);
		doctorBM.setFirstName(doctorDetails.getFirstName());
		doctorBM.setMiddleName(doctorDetails.getMiddleName());
		doctorBM.setLastName(doctorDetails.getLastName());
		doctorBM.setSaluationCode(doctorDetails.getSaluation());
		doctorBM.setContactDetailList(contactList);
		doctorBM.setDoctorEspecialtyList(doctorEspecialityList);
		doctorManager.addDoctor(doctorBM);
}
	public ListRange getDoctors(
									  String doctorId, 
									  String department, 
									  String speciality, 
									  String name,
									  String roomNo,
									  Double consultationChargeFrom,
									  Double consultationChargeTo,
									  String joiningDateFrom,
									  String joiningDateTo,int start, int count, String orderBy ) {
	
		ListRange listRange = new ListRange();
		Integer doctorCode = null;
		if( doctorId != null && ! doctorId.equals("")){
			doctorCode =  new Integer( doctorId );
		}

		Date joiningDate = Converters.getDate(joiningDateFrom);
		Date joiningdateto =  Converters.getDate(joiningDateTo);
		List<DoctorSummaryBM> doctorSummaryList = doctorManager.findDoctors(
																				doctorCode, 
																				department, 
																				speciality, 
																				name, 
																				roomNo, 
																				consultationChargeFrom, 
																				consultationChargeTo, 
																				joiningDate, 
																				joiningdateto, start, count, orderBy);

		
		if(doctorSummaryList!=null && !doctorSummaryList.isEmpty()) {
			List<DoctorSummary> pageSizeData = new ArrayList<DoctorSummary>();
			List<DoctorSummary> doctorSummaries = new ArrayList<DoctorSummary>();
			Iterator<DoctorSummaryBM> doctorSummaryIter = doctorSummaryList.iterator();

			while(doctorSummaryIter.hasNext()) {
		    	 
		    	 DoctorSummaryBM doctorSummaryBM = doctorSummaryIter.next();
		    	 DoctorSummary doctorSummary = new DoctorSummary();
		    	 if(doctorSummaryBM.getConsultationCharge()!=null) {
		    		 doctorSummary.setConsultationCharge(doctorSummaryBM.getConsultationCharge().toString());
		    	 }
		    	 doctorSummary.setDepartment(doctorSummaryBM.getDepartmentCode().getDescription());
		    	 doctorSummary.setDoctorId(doctorSummaryBM.getDoctor().getCode());
		    	 doctorSummary.setDoctorName(doctorSummaryBM.getDoctor().getDescription());
		    	 doctorSummary.setEspeciality(doctorSummaryBM.getEspecialtyCode().getDescription());
		    	 if(doctorSummaryBM.getGender()!=null) {
		    		 doctorSummary.setGender(doctorSummaryBM.getGender().getDescription());
		    	 }
		    	 doctorSummary.setKnownLanguages(doctorSummaryBM.getKnownLanguages());
		    	 doctorSummary.setParmanent(doctorSummaryBM.getPermanent()?"Yes":"No");
		    	 if(doctorSummaryBM.getRoomCode()!=null) {
		    	 doctorSummary.setRoomNo(doctorSummaryBM.getRoomCode().getDescription());
		    	 }
		    	 doctorSummaries.add(doctorSummary);
		     }
			
			int index = 0;
			while( (start+index < start + count) && (doctorSummaries.size() > start+index) ){
				pageSizeData.add(doctorSummaries.get(start+index));
					index++;
			}
			
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(doctorSummaries.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
			
		}
		return listRange;
	}
	
	public void removeDoctors(List<Integer> doctorIdList) {
		try {
			
			doctorManager.removeDoctor(doctorIdList);

		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public ListRange getDoctorDetails(Integer doctorId,int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			
			DoctorBM doctorBm = doctorManager.getDoctorDetail(doctorId);
			DoctorDetails doctorDetails = new DoctorDetails();
			doctorDetails.setActive(doctorBm.getActive());
			doctorDetails.setFirstName(doctorBm.getFirstName());
			doctorDetails.setMiddleName(doctorBm.getMiddleName());
			doctorDetails.setLastName(doctorBm.getLastName());
			doctorDetails.setDoctorDetail(doctorBm.getDoctorDetail());
			doctorDetails.setSaluationCode(doctorBm.getSaluationCode());
			
			if(doctorDetails.getDoctorDetail().getBloodGroup()==null) {
				doctorDetails.getDoctorDetail().setBloodGroup(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getDoctorDetail().getGender()==null) {
				doctorDetails.getDoctorDetail().setGender(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorDetail().getIdProof()==null) {
				doctorDetails.getDoctorDetail().setIdProof(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorDetail().getMarital()==null) {
				doctorDetails.getDoctorDetail().setMarital(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorDetail().getReligion()==null) {
				doctorDetails.getDoctorDetail().setReligion(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getSaluationCode()==null) {
				doctorDetails.setSaluationCode(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorDetail().getDoctor() == null) {
				doctorDetails.getDoctorDetail().setDoctor(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getDoctorDetail().getNationality() == null) {
				doctorDetails.getDoctorDetail().setNationality(new CodeAndDescription(null,null));
			}
			
			if(doctorBm.getDoctorEspecialtyList()!=null && doctorBm.getDoctorEspecialtyList().size()>0) {
				doctorDetails.setDoctorEspecialty(doctorBm.getDoctorEspecialtyList().get(0));
			}
			if(doctorDetails.getDoctorEspecialty().getDepartmentCode()==null) {
				doctorDetails.getDoctorEspecialty().setDepartmentCode(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorEspecialty().getEspecialtyCode() == null) {
				doctorDetails.getDoctorEspecialty().setEspecialtyCode(new CodeAndDescription(null,null));
			}
			
			if(doctorDetails.getDoctorEspecialty().getRoomCode() == null) {
				doctorDetails.getDoctorEspecialty().setRoomCode(new CodeAndDescription(null,null));
			}

			if(doctorBm.getContactDetailList()!=null && doctorBm.getContactDetailList().size()>0) {
				Iterator<ContactDetailsBM> contactDetailsIter = doctorBm.getContactDetailList().iterator();
				while(contactDetailsIter.hasNext()) {
					ContactDetailsBM contactDetailsBM = contactDetailsIter.next();
					if(contactDetailsBM.getContactType().getCode().equals(Constants.CURRENT)) {
						doctorDetails.setCurrentContactDetail(contactDetailsBM);
						
					}else if(contactDetailsBM.getContactType().getCode().equals(Constants.PERMANENT)) {
						doctorDetails.setPermanentContactDetail(contactDetailsBM);
					}else {
						doctorDetails.setEmergencyContactDetail(contactDetailsBM);
					}
				}	
			}
			if(doctorDetails.getCurrentContactDetail().getCountry() == null) {
				doctorDetails.getCurrentContactDetail().setCountry(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getCurrentContactDetail().getState()== null) {
				doctorDetails.getCurrentContactDetail().setState(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getPermanentContactDetail().getCountry() == null) {
				doctorDetails.getPermanentContactDetail().setCountry(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getPermanentContactDetail().getState()== null) {
				doctorDetails.getPermanentContactDetail().setState(new CodeAndDescription(null,null));
			}
			if(doctorDetails.getEmergencyContactDetail()!=null || doctorDetails.getEmergencyContactDetail()==null) {
				if(doctorDetails.getEmergencyContactDetail()==null) {
					ContactDetailsBM emergencyEmptyBm = new ContactDetailsBM();
					doctorDetails.setEmergencyContactDetail(emergencyEmptyBm);
				}
				if(doctorDetails.getEmergencyContactDetail().getCountry() == null) {
					doctorDetails.getEmergencyContactDetail().setCountry(new CodeAndDescription(null,null));
				}
				if(doctorDetails.getEmergencyContactDetail().getGender() == null) {
					doctorDetails.getEmergencyContactDetail().setGender(new CodeAndDescription(null,null));
				}
				if(doctorDetails.getEmergencyContactDetail().getRelationCode()==null) {
					doctorDetails.getEmergencyContactDetail().setRelationCode(new CodeAndDescription(null,null));
				}
				if(doctorDetails.getEmergencyContactDetail().getSalutation() == null) {
					doctorDetails.getEmergencyContactDetail().setSalutation(new CodeAndDescription(null,null));
				}
				if(doctorDetails.getEmergencyContactDetail().getState() == null) {
					doctorDetails.getEmergencyContactDetail().setState(new CodeAndDescription(null,null));
				}
				if(doctorDetails.getEmergencyContactDetail().getContactType() == null){
					doctorDetails.getEmergencyContactDetail().setContactType(new CodeAndDescription(null,null));
				}
			}
			
			Object [] obj = new Object[1];
			obj[0] = doctorDetails;
			listRange.setData(obj);
			listRange.setTotalSize(1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	
	public void modifyDoctorDetails(DoctorDetailBM doctorDetails,
            DoctorEspecialtyBM doctorEspectialities,
            ContactDetailsBM currentContactDetails,
            ContactDetailsBM permanentContactDetails,
            ContactDetailsBM emergencyContactDetails,
            Integer doctorId) {
		try {
			doctorDetails.setDoctor(new CodeAndDescription(doctorId.toString(),""));
			doctorDetails.setBloodGroup(Converters.getEntityValue(doctorDetails.getBloodGroup()));
			doctorDetails.setGender(Converters.getEntityValue(doctorDetails.getGender()));
			doctorDetails.setIdProof(Converters.getEntityValue(doctorDetails.getIdProof()));
			doctorDetails.setMarital(Converters.getEntityValue(doctorDetails.getMarital()));
			doctorDetails.setReligion(Converters.getEntityValue(doctorDetails.getReligion()));
			doctorDetails.setSaluation(Converters.getEntityValue(doctorDetails.getSaluation()));
			doctorDetails.setNationality(Converters.getEntityValue(doctorDetails.getNationality()));
			
			doctorEspectialities.setDepartmentCode(Converters.getEntityValue(doctorEspectialities.getDepartmentCode()));
			doctorEspectialities.setEspecialtyCode(Converters.getEntityValue(doctorEspectialities.getEspecialtyCode()));
			doctorEspectialities.setRoomCode(Converters.getEntityValue(doctorEspectialities.getRoomCode()));
			
			List<ContactDetailsBM> contactList = new ArrayList<ContactDetailsBM>();
			if(currentContactDetails.getFirstName()!=null && !currentContactDetails.getFirstName().equals("")) {
			currentContactDetails.setContactType(new CodeAndDescription(Constants.CURRENT,Constants.CURRENT));
			currentContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
			
			currentContactDetails.setGender(Converters.getEntityValue(currentContactDetails.getGender()));
			currentContactDetails.setRelationCode(Converters.getEntityValue(currentContactDetails.getRelationCode()));
			currentContactDetails.setSalutation(Converters.getEntityValue(currentContactDetails.getSalutation()));
			currentContactDetails.setState(Converters.getEntityValue(currentContactDetails.getState()));
			currentContactDetails.setCountry(Converters.getEntityValue(currentContactDetails.getCountry()));
			
			contactList.add(currentContactDetails);
			}else {
				currentContactDetails = null;
			}
			
			if(permanentContactDetails.getFirstName()!= null && !permanentContactDetails.getFirstName().equals("")) {
				permanentContactDetails.setContactType(new CodeAndDescription(Constants.PERMANENT,Constants.PERMANENT));
				permanentContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
				
				permanentContactDetails.setCountry(Converters.getEntityValue(permanentContactDetails.getCountry()));
				permanentContactDetails.setGender(Converters.getEntityValue(permanentContactDetails.getGender()));
				permanentContactDetails.setRelationCode(Converters.getEntityValue(permanentContactDetails.getRelationCode()));
				permanentContactDetails.setSalutation(Converters.getEntityValue(permanentContactDetails.getSalutation()));
				permanentContactDetails.setState(Converters.getEntityValue(permanentContactDetails.getState()));
				
				contactList.add(permanentContactDetails);
				
			}else {
				permanentContactDetails = null;
			}
			
			if(emergencyContactDetails.getFirstName()!=null && !emergencyContactDetails.getFirstName().equals("")) {
				emergencyContactDetails.setContactType(new CodeAndDescription(Constants.EMERGENCY_PRIMARY,Constants.EMERGENCY_PRIMARY));
				emergencyContactDetails.setForEntity(new CodeAndDescription(Constants.DOCTOR,Constants.DOCTOR));
				contactList.add(emergencyContactDetails);
				
				emergencyContactDetails.setCountry(Converters.getEntityValue(emergencyContactDetails.getCountry()));
				emergencyContactDetails.setGender(Converters.getEntityValue(emergencyContactDetails.getGender()));
				emergencyContactDetails.setRelationCode(Converters.getEntityValue(emergencyContactDetails.getRelationCode()));
				emergencyContactDetails.setSalutation(Converters.getEntityValue(emergencyContactDetails.getSalutation()));
				emergencyContactDetails.setState(Converters.getEntityValue(emergencyContactDetails.getState()));
				
			} else {
				emergencyContactDetails = null;
			}
			List<DoctorEspecialtyBM> doctorEspecialityList = new ArrayList<DoctorEspecialtyBM>();
			doctorEspecialityList.add(doctorEspectialities);
			DoctorBM modifiedDoctorBM = new DoctorBM();
			modifiedDoctorBM.setDoctorId(doctorId);
			modifiedDoctorBM.setActive(true);
			modifiedDoctorBM.setDoctorDetail(doctorDetails);
			modifiedDoctorBM.setFirstName(doctorDetails.getFirstName());
			modifiedDoctorBM.setMiddleName(doctorDetails.getMiddleName());
			modifiedDoctorBM.setLastName(doctorDetails.getLastName());
			modifiedDoctorBM.setSaluationCode(doctorDetails.getSaluation());
			modifiedDoctorBM.setContactDetailList(contactList);
			modifiedDoctorBM.setDoctorEspecialtyList(doctorEspecialityList);
			
			doctorManager.modifyDoctor(modifiedDoctorBM);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

}
