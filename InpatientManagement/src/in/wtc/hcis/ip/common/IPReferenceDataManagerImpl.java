package in.wtc.hcis.ip.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.BedSpecialFeatureAvailability;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.List;

import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.ActionStatusDAO;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.ActivityTypeDAO;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionDAO;
import com.wtc.hcis.ip.da.AdmissionEntryPoint;
import com.wtc.hcis.ip.da.AdmissionEntryPointDAO;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.AdmissionStatusDAO;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedMasterDAO;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedSpecialFeature;
import com.wtc.hcis.ip.da.BedSpecialFeatureDAO;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedStatusDAO;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.BedTypeDAO;
import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.ClaimSponsorDAO;
import com.wtc.hcis.ip.da.CreditClass;
import com.wtc.hcis.ip.da.CreditClassDAO;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DischargeTypeDAO;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupDAO;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderStatusDAO;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDAO;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.DoctorOrderTypeDAO;
import com.wtc.hcis.ip.da.FeedbackTypeDAO;
import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.InsurerDAO;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.NursingUnitTypeDAO;
import com.wtc.hcis.ip.da.ReservationStatus;
import com.wtc.hcis.ip.da.ReservationStatusDAO;
import com.wtc.hcis.ip.da.SponsorClaimStatus;
import com.wtc.hcis.ip.da.SponsorClaimStatusDAO;
import com.wtc.hcis.ip.da.SponsorInsurerAssociation;
import com.wtc.hcis.ip.da.SponsorInsurerAssociationDAO;
import com.wtc.hcis.ip.da.SponsorType;
import com.wtc.hcis.ip.da.SponsorTypeDAO;
import com.wtc.hcis.ip.da.NursingUnitDAO;
import com.wtc.hcis.ip.da.extn.BedPoolDAOExtn;
import com.wtc.hcis.ip.da.extn.InsurancePlansDAOExtn;

/**
 * @author Alok Ranjan
 * 
 */
public class IPReferenceDataManagerImpl implements IPReferenceDataManager {

	private ActivityTypeDAO activityTypeDAO;
	private BedStatusDAO bedStatusDAO;
	private ActionStatusDAO actionStatusDAO;
	private AdmissionEntryPointDAO admissionEntryPointDAO;
	private AdmissionStatusDAO admissionStatusDAO;
	private BedTypeDAO bedTypeDAO;
	private ClaimSponsorDAO claimSponsorsDAO;
	private CreditClassDAO creditClassDAO;
	private DischargeTypeDAO dischargeTypeDAO;
	private DoctorOrderStatusDAO doctorOrderStatusDAO;
	private DoctorOrderTypeDAO doctorOrderTypeDAO;
	private BedSpecialFeatureDAO bedSpecialFeatureDAO;
	private FeedbackTypeDAO feedbackTypeDAO;
	private NursingUnitTypeDAO nursingUnitTypeDAO;
	private ReservationStatusDAO reservationStatusDAO;
	private SponsorClaimStatusDAO sponsorClaimStatusDAO;
	private SponsorTypeDAO sponsorTypeDAO;
	private NursingUnitDAO nursingUnitDAO;
	private BedPoolDAOExtn bedPoolDAO;
	private DoctorOrderTemplateDAO doctorOrderTemplateDAO;
	private DoctorOrderGroupDAO doctorOrderGroupDAO;
	private InsurancePlansDAOExtn insurancePlansDAO;
	private SponsorInsurerAssociationDAO sponsorInsurerAssociationDAO;
	private InsurerDAO insurerDAO;
	private AdmissionDAO admissionDAO;
	private BedMasterDAO bedMasterDAO;


	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getActionStatus()
	 */
	public ListRange getActionStatus(int start, int count, String orderBy) {

		ListRange listRange = new ListRange();
		try {
			List<ActionStatus> actionStatusList = actionStatusDAO.findAll();
			Object[] codeDescObj = new Object[actionStatusList.size()];
			if (actionStatusList != null && actionStatusList.size() > 0) {
				for (int i = 0; i < actionStatusList.size(); i++) {
					ActionStatus tmp = actionStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getActionStatusCd());
					codeAndDescription.setDescription(tmp.getActionStatusDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(actionStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getActivityType()
	 */
	public ListRange getActivityType(int start, int count, String orderBy) {

		ListRange listRange = new ListRange();
		try {
			List<ActivityType> activityTypeList = activityTypeDAO.findAll();
			Object[] codeDescObj = new Object[activityTypeList.size()];
			if (activityTypeList != null && activityTypeList.size() > 0) {
				for (int i = 0; i < activityTypeList.size(); i++) {
					ActivityType tmp = activityTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getActivityTypeCd());
					codeAndDescription.setDescription(tmp.getActivityDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(activityTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/**
	 * 
	 */
	public ListRange getActivityTypeForGroup(String activityGroupName, int start,
			int count, String orderBy) {

		ListRange listRange = new ListRange();
		try {
			List<ActivityType> activityTypeList = activityTypeDAO.findByActivityGroup(activityGroupName);
			Object[] codeDescObj = new Object[activityTypeList.size()];
			if (activityTypeList != null && activityTypeList.size() > 0) {
				for (int i = 0; i < activityTypeList.size(); i++) {
					ActivityType tmp = activityTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getActivityTypeCd());
					codeAndDescription.setDescription(tmp.getActivityDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(activityTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getAdmissionEntryPoints()
	 */
	public ListRange getAdmissionEntryPoints(int start, int count,
			String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<AdmissionEntryPoint> admissionEntryPointList = admissionEntryPointDAO.findAll();
			Object[] codeDescObj = new Object[admissionEntryPointList.size()];
			if (admissionEntryPointList != null && admissionEntryPointList.size() > 0) {
				for (int i = 0; i < admissionEntryPointList.size(); i++) {
					AdmissionEntryPoint tmp = admissionEntryPointList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getEntryPointName());
					codeAndDescription.setDescription(tmp.getEntryPointDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(admissionEntryPointList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getAdmissionStatus()
	 */
	public ListRange getAdmissionStatus(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<AdmissionStatus> admissionStatusList = admissionStatusDAO.findAll();
			Object[] codeDescObj = new Object[admissionStatusList.size()];
			if (admissionStatusList != null && admissionStatusList.size() > 0) {
				for (int i = 0; i < admissionStatusList.size(); i++) {
					AdmissionStatus tmp = admissionStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getAdmissionStatusCd());
					codeAndDescription.setDescription(tmp.getAdmissionStatusDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(admissionStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getBedStatus()
	 */
	public ListRange getBedStatus(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<BedStatus> bedStatusList = bedStatusDAO.findByActiveFlag( ApplicationConstants.ACTIVE_FLAG_YES ); 
			Object[] codeDescObj = new Object[bedStatusList.size()];
			if (bedStatusList != null && bedStatusList.size() > 0) {
				for (int i = 0; i < bedStatusList.size(); i++) {
					BedStatus tmp = bedStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getBedStatusCd());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(bedStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getBedType()
	 */
	public ListRange getBedType(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<BedType> bedTypeList = bedTypeDAO.findByActiveFlag(ApplicationConstants.ACTIVE_FLAG_YES);
			Object[] codeDescObj = new Object[bedTypeList.size()];
			if (bedTypeList != null && bedTypeList.size() > 0) {
				for (int i = 0; i < bedTypeList.size(); i++) {
					BedType tmp = bedTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getBedTypeCd());
					codeAndDescription.setDescription(tmp.getBedTypeDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(bedTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getClaimSponsors()
	 */
	public ListRange getClaimSponsors(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<ClaimSponsor> claimSponsorList = claimSponsorsDAO.findAll();
			Object[] codeDescObj = new Object[claimSponsorList.size()];
			if (claimSponsorList != null && claimSponsorList.size() > 0) {
				for (int i = 0; i < claimSponsorList.size(); i++) {
					ClaimSponsor tmp = claimSponsorList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getSponsorsName());
					codeAndDescription.setDescription(tmp.getSponsorDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(claimSponsorList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getCreditClass()
	 */
	public ListRange getCreditClass(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<CreditClass> creditClassList = creditClassDAO.findAll();
			Object[] codeDescObj = new Object[creditClassList.size()];
			if (creditClassList != null && creditClassList.size() > 0) {
				for (int i = 0; i < creditClassList.size(); i++) {
					CreditClass tmp = creditClassList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getCreditClassCd());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(creditClassList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getDischargeType()
	 */
	public ListRange getDischargeType(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<DischargeType> dischargeTypeList = dischargeTypeDAO.findAll();
			Object[] codeDescObj = new Object[dischargeTypeList.size()];
			if (dischargeTypeList != null && dischargeTypeList.size() > 0) {
				for (int i = 0; i < dischargeTypeList.size(); i++) {
					DischargeType tmp = dischargeTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getDischargeTypeCd());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(dischargeTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getDoctorOrderStatus()
	 */
	public ListRange getDoctorOrderStatus(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<DoctorOrderStatus> doctorOrderStatusList = doctorOrderStatusDAO.findAll();
			Object[] codeDescObj = new Object[doctorOrderStatusList.size()];
			if (doctorOrderStatusList != null && doctorOrderStatusList.size() > 0) {
				for (int i = 0; i < doctorOrderStatusList.size(); i++) {
					DoctorOrderStatus tmp = doctorOrderStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getOrderStatusCd());
					codeAndDescription.setDescription(tmp.getStatusDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(doctorOrderStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	public ListRange getDoctorOrderTemplate(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<DoctorOrderTemplate> doctorOrderTemplateList = doctorOrderTemplateDAO.findAll();
			Object[] codeDescObj = new Object[doctorOrderTemplateList.size()];
			if (doctorOrderTemplateList != null && doctorOrderTemplateList.size() > 0) {
				for (int i = 0; i < doctorOrderTemplateList.size(); i++) {
					DoctorOrderTemplate tmp = doctorOrderTemplateList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getTemplateId());
					codeAndDescription.setDescription(tmp.getTemplateDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(doctorOrderTemplateList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	
	public ListRange getDoctorOrderGroup(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<DoctorOrderGroup> doctorOrderGroupList = doctorOrderGroupDAO.findAll();
			Object[] codeDescObj = new Object[doctorOrderGroupList.size()];
			if (doctorOrderGroupList != null && doctorOrderGroupList.size() > 0) {
				for (int i = 0; i < doctorOrderGroupList.size(); i++) {
					DoctorOrderGroup tmp = doctorOrderGroupList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getOrderGroupName());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(doctorOrderGroupList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getDoctorOrderType()
	 */
	public ListRange getDoctorOrderType(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<DoctorOrderType> doctorOrderTypeList = doctorOrderTypeDAO.findAll();
			Object[] codeDescObj = new Object[doctorOrderTypeList.size()];
			if (doctorOrderTypeList != null && doctorOrderTypeList.size() > 0) {
				for (int i = 0; i < doctorOrderTypeList.size(); i++) {
					DoctorOrderType tmp = doctorOrderTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getOrderTypeCd());
					codeAndDescription.setDescription(tmp.getOrderTypeDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(doctorOrderTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getEffectiveBedSpecialFeatures()
	 */
	public ListRange getBedSpecialFeatures(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<BedSpecialFeature> bedSpecialFeatureList = bedSpecialFeatureDAO.findAll();
			Object[] codeDescObj = new Object[bedSpecialFeatureList.size()];
			if (bedSpecialFeatureList != null && bedSpecialFeatureList.size() > 0) {
				for (int i = 0; i < bedSpecialFeatureList.size(); i++) {
					BedSpecialFeature tmp = bedSpecialFeatureList.get(i);
					BedSpecialFeatureAvailability retVal = new BedSpecialFeatureAvailability();
					retVal.setFeatureName(tmp.getFeatureName());
					retVal.setDescription(tmp.getDescription());
					retVal.setAvailabilityFlag(Boolean.FALSE);
					codeDescObj[i] = retVal;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(bedSpecialFeatureList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getFeedbackType()
	 */
	public ListRange getFeedbackType(String involvedProcess, int start,
			int count, String orderBy) {
		// List<FeedbackType>feedbackTypeList =
		// feedbackTypeDAO.findByInvolvedProcess(involvedProcess);
		//		
		// if ( feedbackTypeList != null ) {
		// List<CodeAndDescription>feedbackTypeCodeAndDescList = new
		// ArrayList<CodeAndDescription>();
		//			
		// for ( FeedbackType feedbackType : feedbackTypeList ) {
		// feedbackTypeCodeAndDescList.add( new CodeAndDescription(
		// feedbackType.getId(), feedbackType.getDescription() ) );
		// }
		//			
		// return feedbackTypeCodeAndDescList;
		// } else {
		// return null;
		// }

		return null; // TODO : review this
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getNursingUnitType()
	 */
	public ListRange getNursingUnitType(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<NursingUnitType> nursingUnitTypeList = nursingUnitTypeDAO.findAll();
			Object[] codeDescObj = new Object[nursingUnitTypeList.size()];
			if (nursingUnitTypeList != null && nursingUnitTypeList.size() > 0) {
				for (int i = 0; i < nursingUnitTypeList.size(); i++) {
					NursingUnitType tmp = nursingUnitTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getUnitTypeCd());
					codeAndDescription.setDescription(tmp.getUnitTypeDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(nursingUnitTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	public ListRange getNursingUnits(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<NursingUnit> nursingUnitList = nursingUnitDAO.findAll();
			Object[] codeDescObj = new Object[nursingUnitList.size()];
			if (nursingUnitList != null && nursingUnitList.size() > 0) {
				for (int i = 0; i < nursingUnitList.size(); i++) {
					NursingUnit tmp = nursingUnitList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getUnitName());
					codeAndDescription.setDescription(tmp.getUnitDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(nursingUnitList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getReservationStatus()
	 */
	public ListRange getReservationStatus(int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		try {
			List<ReservationStatus> reservationStatusList = reservationStatusDAO.findAll();
			Object[] codeDescObj = new Object[reservationStatusList.size()];
			if (reservationStatusList != null && reservationStatusList.size() > 0) {
				for (int i = 0; i < reservationStatusList.size(); i++) {
					ReservationStatus tmp = reservationStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getReservationStatusCd());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(reservationStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getSponsorClaimStatus()
	 */
	public ListRange getSponsorClaimStatus(int start, int count, String orderBy) {

		ListRange listRange = new ListRange();
		try {
			List<SponsorClaimStatus> sponsorClaimStatusList = sponsorClaimStatusDAO.findAll();
			Object[] codeDescObj = new Object[sponsorClaimStatusList.size()];
			if (sponsorClaimStatusList != null && sponsorClaimStatusList.size() > 0) {
				for (int i = 0; i < sponsorClaimStatusList.size(); i++) {
					SponsorClaimStatus tmp = sponsorClaimStatusList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getClaimStatusCd());
					codeAndDescription.setDescription(tmp.getClaimStatusDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(sponsorClaimStatusList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.common.IPReferenceDataManager#getSponsorType()
	 */
	public ListRange getSponsorType(int start, int count, String orderBy) {

		ListRange listRange = new ListRange();
		try {
			List<SponsorType> sponsorTypeList = sponsorTypeDAO.findAll();
			Object[] codeDescObj = new Object[sponsorTypeList.size()];
			if (sponsorTypeList != null && sponsorTypeList.size() > 0) {
				for (int i = 0; i < sponsorTypeList.size(); i++) {
					SponsorType tmp = sponsorTypeList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getSponsorTypeCd());
					codeAndDescription.setDescription(tmp.getSponsorDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(sponsorTypeList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}


	public ListRange getBedPools(int start, int count, String orderBy)
			throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<BedPool> bedPoolsList = bedPoolDAO.findAll();
			Object[] codeDescObj = new Object[bedPoolsList.size()];
			if (bedPoolsList != null && bedPoolsList.size() > 0) {
				for (int i = 0; i < bedPoolsList.size(); i++) {
					BedPool tmp = bedPoolsList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getBedPoolName());
					codeAndDescription.setDescription(tmp.getPoolDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(bedPoolsList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	
	
	/**
	 * This method returns the list of insurers which are associated with given sponsors.
	 * If no sponsor is specified, it returns all insurers which at least one association (with any of sponsor)
	 */
	public ListRange getInsurerForSponsor(String sponsorName ,int start, int count, String orderBy)throws HCISException{
		
		ListRange listRange = new ListRange();
		List<SponsorInsurerAssociation> sponsorInsurerAssociationList;
		try {
			
			if(sponsorName != null && sponsorName.length() > 0){
				 sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.sponsorName", sponsorName );
			}else{
				 sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findAll();
			}
			
			List<Insurer> insurerList = new ArrayList<Insurer>(0);
			
			if( sponsorInsurerAssociationList != null && !sponsorInsurerAssociationList.isEmpty() ){
				for( SponsorInsurerAssociation association : sponsorInsurerAssociationList ){
					
					Insurer insurer = insurerDAO.findById( association.getId().getInsurerName());
					
					if( insurer != null ){
						insurerList.add( insurer );
					}
				}
			}
			
			Object[] codeDescObj = new Object[insurerList.size()];
			if (insurerList != null && insurerList.size() > 0) {
				for (int i = 0; i < insurerList.size(); i++) {
					Insurer tmp = insurerList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getInsurerName());
					codeAndDescription.setDescription(tmp.getInsurerDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(insurerList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getInsurer(int start, int count, String orderBy)throws HCISException{
		
		ListRange listRange = new ListRange();
		try {
			List<Insurer> insurerList = insurerDAO.findAll();
			Object[] codeDescObj = new Object[insurerList.size()];
			if (insurerList != null && insurerList.size() > 0) {
				for (int i = 0; i < insurerList.size(); i++) {
					Insurer tmp = insurerList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getInsurerName() );
					codeAndDescription.setDescription(tmp.getInsurerDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(insurerList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	public ListRange getPlanForInsurer(String insurerName ,int start, int count, String orderBy)throws HCISException{
		
		ListRange listRange = new ListRange();
		try {
			List<InsurancePlans> insurancePlansList = insurancePlansDAO.getActivePlansForInsurer(insurerName);
			Object[] codeDescObj = new Object[insurancePlansList.size()];
			if (insurancePlansList != null && insurancePlansList.size() > 0) {
				for (int i = 0; i < insurancePlansList.size(); i++) {
					InsurancePlans tmp = insurancePlansList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getPlanCd());
					codeAndDescription.setDescription(tmp.getPlanName());
					codeDescObj[i] = codeAndDescription;
				}
			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(insurancePlansList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	
	public ListRange getAdmissionReqNbr( int start, int count, String orderBy)throws HCISException{
		ListRange listRange = new ListRange();
		try {
			List<Admission> admissionList = admissionDAO.findAll();
			Object[] codeDescObj = new Object[admissionList.size()];
			if (admissionList != null && admissionList.size() > 0) {
				for (int i = 0; i < admissionList.size(); i++) {
					Admission tmp = admissionList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getAdmissionReqNbr().toString());
					codeAndDescription.setDescription( tmp.getAdmissionReqNbr().toString() );//keep it same 
					codeDescObj[i] = codeAndDescription;
				}
			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(admissionList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	
	public ListRange getBedMaster( String bedType, int start, int count, String orderBy)throws HCISException{
		ListRange listRange = new ListRange();
		try {
			List<BedMaster> bedMasterList = bedMasterDAO.findByProperty("bedType.bedTypeCd", bedType);
			Object[] codeDescObj = new Object[bedMasterList.size()];
			if (bedMasterList != null && bedMasterList.size() > 0) {
				for (int i = 0; i < bedMasterList.size(); i++) {
					BedMaster bedMaster = bedMasterList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(bedMaster.getBedNumber());
					codeAndDescription.setDescription( bedMaster.getDescription() );//keep it same 
					codeDescObj[i] = codeAndDescription;
				}
			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(bedMasterList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	
	
	
	public void setActivityTypeDAO(ActivityTypeDAO activityTypeDAO) {
		this.activityTypeDAO = activityTypeDAO;
	}

	public void setBedStatusDAO(BedStatusDAO bedStatusDAO) {
		this.bedStatusDAO = bedStatusDAO;
	}

	public void setActionStatusDAO(ActionStatusDAO actionStatusDAO) {
		this.actionStatusDAO = actionStatusDAO;
	}

	public void setAdmissionEntryPointDAO(
			AdmissionEntryPointDAO admissionEntryPointDAO) {
		this.admissionEntryPointDAO = admissionEntryPointDAO;
	}

	public void setAdmissionStatusDAO(AdmissionStatusDAO admissionStatusDAO) {
		this.admissionStatusDAO = admissionStatusDAO;
	}

	public void setBedTypeDAO(BedTypeDAO bedTypeDAO) {
		this.bedTypeDAO = bedTypeDAO;
	}

	public void setClaimSponsorsDAO(ClaimSponsorDAO claimSponsorsDAO) {
		this.claimSponsorsDAO = claimSponsorsDAO;
	}

	public void setCreditClassDAO(CreditClassDAO creditClassDAO) {
		this.creditClassDAO = creditClassDAO;
	}

	public void setDischargeTypeDAO(DischargeTypeDAO dischargeTypeDAO) {
		this.dischargeTypeDAO = dischargeTypeDAO;
	}

	public void setDoctorOrderStatusDAO(DoctorOrderStatusDAO doctorOrderStatusDAO) {
		this.doctorOrderStatusDAO = doctorOrderStatusDAO;
	}

	public void setDoctorOrderTypeDAO(DoctorOrderTypeDAO doctorOrderTypeDAO) {
		this.doctorOrderTypeDAO = doctorOrderTypeDAO;
	}

	public void setBedSpecialFeatureDAO(BedSpecialFeatureDAO bedSpecialFeatureDAO) {
		this.bedSpecialFeatureDAO = bedSpecialFeatureDAO;
	}

	public void setFeedbackTypeDAO(FeedbackTypeDAO feedbackTypeDAO) {
		this.feedbackTypeDAO = feedbackTypeDAO;
	}

	public void setNursingUnitTypeDAO(NursingUnitTypeDAO nursingUnitTypeDAO) {
		this.nursingUnitTypeDAO = nursingUnitTypeDAO;
	}

	public void setReservationStatusDAO(ReservationStatusDAO reservationStatusDAO) {
		this.reservationStatusDAO = reservationStatusDAO;
	}

	public void setSponsorClaimStatusDAO(SponsorClaimStatusDAO sponsorClaimStatusDAO) {
		this.sponsorClaimStatusDAO = sponsorClaimStatusDAO;
	}

	public void setSponsorTypeDAO(SponsorTypeDAO sponsorTypeDAO) {
		this.sponsorTypeDAO = sponsorTypeDAO;
	}

	public void setNursingUnitDAO(NursingUnitDAO nursingUnitDAO) {
		this.nursingUnitDAO = nursingUnitDAO;
	}

	public void setBedPoolDAO(BedPoolDAOExtn bedPoolDAO) {
		this.bedPoolDAO = bedPoolDAO;
	}

	public void setDoctorOrderTemplateDAO(
			DoctorOrderTemplateDAO doctorOrderTemplateDAO) {
		this.doctorOrderTemplateDAO = doctorOrderTemplateDAO;
	}

	public void setDoctorOrderGroupDAO(DoctorOrderGroupDAO doctorOrderGroupDAO) {
		this.doctorOrderGroupDAO = doctorOrderGroupDAO;
	}

	public void setInsurancePlansDAO(InsurancePlansDAOExtn insurancePlansDAO) {
		this.insurancePlansDAO = insurancePlansDAO;
	}

	public void setSponsorInsurerAssociationDAO(
			SponsorInsurerAssociationDAO sponsorInsurerAssociationDAO) {
		this.sponsorInsurerAssociationDAO = sponsorInsurerAssociationDAO;
	}

	public void setInsurerDAO(InsurerDAO insurerDAO) {
		this.insurerDAO = insurerDAO;
	}

	public void setAdmissionDAO(AdmissionDAO admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public void setBedMasterDAO(BedMasterDAO bedMasterDAO) {
		this.bedMasterDAO = bedMasterDAO;
	}

}
