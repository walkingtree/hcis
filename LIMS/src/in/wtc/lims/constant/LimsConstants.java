/**
 * 
 */
package in.wtc.lims.constant;

/**
 * @author Bhavesh
 *
 */
public interface LimsConstants {

	String CONTEXT_LAB_TYPE = "LAB_TYPE";
	String CONTEXT_TEST_FOR_GENDER = "TEST_FOR_GENDER";
	String CONTEXT_TEST_ATTRIBUTE_TYPE = "TEST_ATTRIBUTE_TYPE";
	String CONTEXT_LAB_MEASUREMENT_UNIT = "LAB_MEASUREMENT_UNIT";
	String CONTEXT_PATIENT_TEST_DETAIL_STATUS = "PATIENT_TEST_DETAIL_STATUS";
	
	String HOSPITAL_NAME = "HOSPITAL_NAME";
	
	// TEST TEMPLATE CONSTANT
	
	String PATIENT_SECTION = "SECTION_1";
	String DOCTOR_SECTION = "SECTION_2";
	String TEST_ATTRIBUTE_SECTION = "SECTION_3";
	String TEAMPLATE = "TEAMPLATE";
		//Widgets
	
	String WIDGET_TYPE_MVL = "MVL";
	String WIDGET_TYPE_STRING= "string";
	String WIDGET_TYPE_NUMBER = "number";
	
	String ATTR_TYPE_TEXT = "TEXT";
	String ATTR_TYPE_OBSERVATION = "OBSERVATION";
	String ATTR_TYPE_NUMERIC = "NUMERIC" ;

	
	//STATUS CONSTANTS
	
		//LAB PATIENT TEST DETAIL STATUS
	
	String PATIENT_TEST_DETAIL = "PATIENT_TEST_DETAIL";
	
	String PATIENT_TEST_STATUS_CREATED = "CREATED";
	String PATIENT_TEST_STATUS_SPECIMEN_COLLECTED = "SPECIMEN_COLLECTED";
	String PATIENT_TEST_STATUS_TEST_PERFORMED = "TEST_PERFORMED";
	String PATIENT_TEST_STATUS_RESULT_ENTERED = "RESULT_ENTERED";
	String PATIENT_TEST_STATUS_APPROVED = "APPROVED";
	String PATIENT_TEST_STATUS_DISAPPROVED = "DISAPPROVED";
	String PATIENT_TEST_STATUS_COLLECTED_BY_PATIENT = "COLLECTED_BY_PATIENT";
	String PATIENT_TEST_STATUS_RESULT_COLLECTED = "RESULT_COLLECTED";
	String PATIENT_TEST_STATUS_RESULT_SHARED = "RESULT_SHARED";
	
	String YES = "Y";
	String NO = "N";
	
	// Test Template Constant
	
	String REMARKS = "REMARKS";
}
