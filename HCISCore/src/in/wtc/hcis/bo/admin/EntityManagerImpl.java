package in.wtc.hcis.bo.admin;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.EntityBM;
import in.wtc.hcis.bm.base.ImagePopertyBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.contact.ContactManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.Validate;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.wtc.hcis.da.BloodGroup;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.Entity;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.Marital;
import com.wtc.hcis.da.Nationality;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.extn.EntityDAOExtn;

public class EntityManagerImpl implements EntityManager {

	ContactManager contactManager;
	EntityDAOExtn entityDAO;
	ContactDetailsDAO contactDAO;
	CommonDataManager commonDataManager;

	static final String CONTEXT_ENTITY_TYPE = "ENTITY_TYPE";
	
	public void addEntity(EntityBM entityBM) throws HCISException {
		try {
			Validate.notNull(entityBM," entityBM must not be null ");
			
			ContactDetails contactDetails = null;
			Entity entity = this.convertEntityBM2DM(entityBM);
			entity.setCreatedBy(entityBM.getUserId());
			entity.setCreatedDtm(Calendar.getInstance().getTime());
			
			
			if( entityBM.getContactDetailsBM() != null){
				contactDetails = contactManager.convertContactDetailsBM2DM(entityBM.getContactDetailsBM(),null);
				contactDAO.save(contactDetails);
				entity.setContactDetails(contactDetails);
			}
			
			
			
			
			//Save image
			
			
			String imageString = null;
			
			if( entityBM.getImage().getFileName() != null  ){
				
				try {
					
					ImagePopertyBM imagePopertyBM =  entityBM.getImage();
					
					File tempImage = new File( imagePopertyBM.getFilePath()+ File.separator + imagePopertyBM.getFileName());
					BufferedImage bi = ImageIO.read( tempImage );
					
				    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        try {
			            ImageIO.write(bi, "jpg", baos);
			            baos.flush();
			            byte[] imageAsRawBytes = baos.toByteArray();
			            baos.close();
			 
			            //bytes to string
			            BASE64Encoder b64enc = new BASE64Encoder();
			            imageString = b64enc.encode(imageAsRawBytes);
			            //don't do this!!!
			            //imageString = new String(imageAsRawBytes);
			        } catch (IOException ex) {
			            throw new  Exception("Failed to convert image into string");
			        }
			        
					tempImage.delete();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Filed to save image");
				}
				
				entity.setImage(imageString);
			}
			//end of image operations.
			
			entityDAO.save(entity);
			
		} catch ( Exception e) {
			Fault fault = new Fault( ApplicationErrors.ENTITY_SAVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public ListRange getEntities(Integer entityId, String name,
			String genderCode, String typeCode, String stateCode,
			String contryCode, String city, String address, String isPermanent,
			int start, int count, String orderBy) {
		
			try {
					List<Entity> entityList = entityDAO.getEntities( entityId, name,
							genderCode, typeCode, stateCode,
							contryCode, city, address, isPermanent,
							start, count);
					
					ListRange listRange = new ListRange();
					
					if( entityList != null && !entityList.isEmpty() ){
						
						int index = 0;
						List<EntityBM> pageSizeData = new ArrayList<EntityBM>(0);
						
						while( (start+index < start + count) && (entityList.size() > start+index) ){
							
							EntityBM entityBM = new EntityBM();
							ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
							
							Entity entity = entityList.get(start+index);
							
							entityBM = convertEntityDM2BM(entity);
							
							contactDetailsBM = contactManager.convertContactDetailsDM2BM(entity.getContactDetails());

							entityBM.setContactDetailsBM(contactDetailsBM);
							
							pageSizeData.add( entityBM );
								index++;
						}
							listRange.setData(pageSizeData.toArray());
							listRange.setTotalSize(entityList.size());
						}else {
							listRange.setData(new Object[0]);
							listRange.setTotalSize(0);
						}
					return listRange;
					
				}catch(Exception ex) {
					Fault fault = new Fault(ApplicationErrors.ENTITY_SEARCH_FAILED);
					throw new HCISException( fault.getFaultMessage() + ex.getMessage(),
							 fault.getFaultCode(),
							 fault.getFaultType() );
				}
	}

	public EntityBM modifyEntity(EntityBM modifiedEntityBM)
			throws HCISException {
		try {
			Validate.notNull(modifiedEntityBM," entityBM must not be null ");
			
			Entity modifiedEntity = this.convertEntityBM2DM(modifiedEntityBM);
			modifiedEntity.setModifiedBy(modifiedEntityBM.getUserId());
			modifiedEntity.setLastModifiedDtm(Calendar.getInstance().getTime());
			
			/*
			 * Contact details modification
			 * 
			 */
			
			  ContactDetailsBM contactDetailsBM = modifiedEntityBM.getContactDetailsBM();

			   if( contactDetailsBM != null){
				   
				   if( contactDetailsBM.getContactDetailsCode() != null ){
						ContactDetails existingcontactDetails = contactDAO.findById( contactDetailsBM.getContactDetailsCode() );
						
						ContactDetails contactDetails = contactManager.convertContactDetailsBM2DM(contactDetailsBM, existingcontactDetails );
						
						contactDAO.attachDirty( contactDetails );
				   }
				}
			
			//Save image
						
						
						String imageString = null;
						
						if( modifiedEntityBM.getImage().getFileName() != null  ){
							
							try {
								
								ImagePopertyBM imagePopertyBM =  modifiedEntityBM.getImage();
								
								File tempImage = new File( imagePopertyBM.getFilePath()+ File.separator + imagePopertyBM.getFileName());
								BufferedImage bi = ImageIO.read( tempImage );
								
							    ByteArrayOutputStream baos = new ByteArrayOutputStream();
						        try {
						            ImageIO.write(bi, "jpg", baos);
						            baos.flush();
						            byte[] imageAsRawBytes = baos.toByteArray();
						            baos.close();
						 
						            //bytes to string
						            BASE64Encoder b64enc = new BASE64Encoder();
						            imageString = b64enc.encode(imageAsRawBytes);
						            //don't do this!!!
						            //imageString = new String(imageAsRawBytes);
						        } catch (IOException ex) {
						            throw new  Exception("Failed to convert image into string");
						        }
						        
								tempImage.delete();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Filed to save image");
							}
							
							modifiedEntity.setImage(imageString);
						}
						//end of image operations.
						
						entityDAO.save(modifiedEntity);
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.ENTITY_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
		return modifiedEntityBM;
	}
	
	/**
	 * This method converts entity business model into entity data model
	 * 
	 * @param entityBM
	 * @param existingEntity
	 * @return
	 */
	private Entity convertEntityBM2DM( EntityBM entityBM) {
		
		Entity entityDM = null;
		if(entityBM.getEntityId()!=null  && !entityBM.getEntityId().equals("")){
			
			 entityDM = entityDAO.findById(entityBM.getEntityId());
		}else {
			entityDM = new Entity();
		}
		
		entityDM.setDob(entityBM.getDob());
		entityDM.setName(entityBM.getName());
		entityDM.setType(entityBM.getTypeCode().getCode());
		entityDM.setKnownLanguages(entityBM.getKnownLanguages());
		entityDM.setQualification(entityBM.getQualification());
		entityDM.setIsPermanent(entityBM.getIsPermanent());
		entityDM.setJoiningDt(entityBM.getJoiningDt());
		entityDM.setLeavingDt(entityBM.getLeavingDt());
		entityDM.setExperience(entityBM.getExperience());
		entityDM.setReferredBy(entityBM.getReferredBy());
		
		if(entityBM.getBloodGroup()!=null ) {
			BloodGroup bloodGroup = new BloodGroup();
			bloodGroup.setBloodGroupCode(entityBM.getBloodGroup().getCode());
			bloodGroup.setDescription(entityBM.getBloodGroup().getDescription());
			entityDM.setBloodGroup(bloodGroup);	
		}
		
		if(entityBM.getGender()!=null ) {
			Gender gender = new Gender();
			gender.setGenderCode(entityBM.getGender().getCode());
			gender.setDescription(entityBM.getGender().getDescription());
			entityDM.setGender(gender);
		}
		
		if(entityBM.getNationality()!=null) {
			Nationality nationality = new Nationality();
			nationality.setNationalityCode(entityBM.getNationality().getCode());
			nationality.setDescription(entityBM.getNationality().getDescription());
			entityDM.setNationality(nationality);
		}
		
		if(entityBM.getMarital()!=null ) {
			Marital marital = new Marital();
			marital.setMaritalCode(entityBM.getMarital().getCode());
			marital.setDescription(entityBM.getMarital().getDescription());
			entityDM.setMarital(marital);
		}
		
		if(entityBM.getSaluation()!=null ) {
			Saluation saluation = new Saluation();
			saluation.setSaluationCode(entityBM.getSaluation().getCode());
			saluation.setDescription(entityBM.getSaluation().getDescription());
			entityDM.setSaluation(saluation);
		}
		
		//entityDM.setContactDetails(entityBM.getContactDetailsBM());
		
		
		return entityDM;
	}
	
	/*
	 * This method converts the Datamodel to business model
	 */
	private EntityBM convertEntityDM2BM(Entity entity) {
		EntityBM entityBM = new EntityBM();


	if(entity.getBloodGroup()!=null) {
		CodeAndDescription bloodGroup = new CodeAndDescription();
		bloodGroup.setCode(entity.getBloodGroup().getBloodGroupCode());
		bloodGroup.setDescription(entity.getBloodGroup().getDescription());
		entityBM.setBloodGroup(bloodGroup);
	}

	entityBM.setDob(entity.getDob());
	entityBM.setEntityId(entity.getEntityId());
	entityBM.setName(entity.getName());
	if(entity.getSaluation()!=null) {
		CodeAndDescription saluation = new CodeAndDescription();
		saluation.setCode(entity.getSaluation().getSaluationCode());
		saluation.setDescription(entity.getSaluation().getDescription());
		entityBM.setSaluation(saluation);	
	}

	if(entity.getGender()!=null) {
		CodeAndDescription gender = new CodeAndDescription();
		gender.setCode(entity.getGender().getGenderCode());
		gender.setDescription(entity.getGender().getDescription());
		entityBM.setGender(gender);	
	}

	entityBM.setJoiningDt(entity.getJoiningDt());
	entityBM.setKnownLanguages(entity.getKnownLanguages());
	entityBM.setLeavingDt(entity.getLeavingDt());

	if(entity.getMarital()!=null) {
		CodeAndDescription marital = new CodeAndDescription();
		marital.setCode(entity.getMarital().getMaritalCode());
		marital.setDescription(entity.getMarital().getDescription());
		entityBM.setMarital(marital);
	}

	entityBM.setIsPermanent(entity.getIsPermanent());
	entityBM.setReferredBy(entity.getReferredBy());
	entityBM.setExperience(entity.getExperience());
	entityBM.setQualification(entity.getQualification());
	entityBM.setReferredBy(entity.getReferredBy());

	if(entity.getNationality() != null ) {
		CodeAndDescription nationality = new CodeAndDescription();
		nationality.setCode(entity.getNationality().getNationalityCode());
		nationality.setDescription(entity.getNationality().getDescription());
		entityBM.setNationality(nationality);
		
	}

	entityBM.setImage( this.getPatientImage(entity));
	
	ReferenceDataList entityType =  commonDataManager.getReferenceData(CONTEXT_ENTITY_TYPE, entity.getType());
	entityBM.setTypeCode(new CodeAndDescription(entityType.getId().getAttrCode(),
														entityType.getAttrDesc()));

	return entityBM;
	}
	
	private ImagePopertyBM getPatientImage( Entity entity) {

		try {
			ImagePopertyBM imagePopertyBM = new ImagePopertyBM();
			//string to ByteArrayInputStream
			
			String imageString = entity.getImage();
			if(imageString != null && !imageString.isEmpty()){
				
				BufferedImage bImage = null;
				BASE64Decoder b64dec = new BASE64Decoder();
				try {
					byte[] output = b64dec.decodeBuffer(imageString);
					ByteArrayInputStream bais = new ByteArrayInputStream(output);
					bImage = ImageIO.read(bais);
				} catch (IOException e) {
					System.err.println("Error");
				}
				
				imagePopertyBM.setBufferedImage(bImage);
				
			}

	        return imagePopertyBM;
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Image for Entity id="+entity.getEntityId()+" not exists. ");
		}
		return null;
	}
	
	public EntityBM getEntityDetail( Integer entityId) throws HCISException{
		
		Entity entity =  entityDAO.findById(entityId);
		
		if(entity == null ){
			throw new HCISException(" Entity with id "+ entityId + " does not exists.","","");
		}
		EntityBM entityBM = convertEntityDM2BM(entity);
		
		ContactDetailsBM contactDetailsBM = contactManager.convertContactDetailsDM2BM(entity.getContactDetails());

		entityBM.setContactDetailsBM(contactDetailsBM);
		return entityBM;
	}

	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}

	public void setEntityDAO(EntityDAOExtn entityDAO) {
		this.entityDAO = entityDAO;
	}

	public void setContactDAO(ContactDetailsDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	


}

