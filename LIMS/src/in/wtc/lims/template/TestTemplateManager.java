package in.wtc.lims.template;

import in.wtc.lims.bm.TestTemplateBM;
import in.wtc.lims.exception.LimsException;


/**
 * 
 * @author Bhavesh
 *
 *	Templates are meant for providing freedom to user, to choose  various widgets 
 *  available for given test and to put them into desire sequence. Widgets can be of 
 *  
 *  Once the template is configured for given test (or test-reagent or test-technique combination) it will be available 
 *  while entering test result and while viewing the test result report.
 *  
 *    This manager provide method to configure new template, modify and to remove(?). 
 */

public interface TestTemplateManager {
	
/**
 * 	
 * @param testCode
 * @return
 */
/**	This method will return dynamic widgets for configuring template for the test.
 *  In this template user can drag any widget from tree and can drop in the specified area.
 *  In this method we have created three list patientWidgetList, doctorWidgetList, and testAttributeWidgetList 
 *  All the patient widget will be in the patientWidgetList , doctorWidget will be in the doctorWidgetList and testAttributeWidget will be in the testAttributeList
 *  From the UI perspective we have three area where user can drop widget from the tree. patientDetail, doctorDetail and testAttributeDetail
 *  these three list will be populate in the tree with different tree node.  
 *	
 * 	
 */
	TestTemplateBM getWidgetsForTest(String testCode) throws LimsException;
/**
 * 	
 * @param testTemplateBM
 * @throws LimsException
 * 
 * Saves new test template for the given test, into the system. For now one test can have only one 
 * template associated with it (There is a scope of configuring multiple template for single test). 
 * 
 * 	 
 * This method will be called after calling above method 'getWidgetsForTest'
 * so expectation from UI is to set the sequenceNuber on these 'TestTemplateBMs'
 * so that their exact position on UI panel will be persisted.
 */
	void addTestTemplate( TestTemplateBM testTemplateBM) throws LimsException;
	
/**
 * This method will return  template id for the test.
 * @param testCode
 * @return   
 * @throws LimsException
 */
	Integer getTemplateId(String testCode) throws LimsException;
	
/**
 * 	
 * @param templateId
 * @return
 * @throws LimsException
 */
	TestTemplateBM getWidgetsForTemplate(String testCode)throws LimsException;
	
	/**
	 * 
	 * @param testTemplateBM
	 * @throws LimsException
	 */
	void modifyTestTemplate( TestTemplateBM testTemplateBM ) throws LimsException;
	
	/**
	 * This method takes patientTestId as parameter and internally it fetches all the
	 * widgets available to the test, fills the values of the  widgets if available
	 * and finally returns TestTemplateBM.
	 * all the widgets 
	 * 
	 * @param serviceUID
	 * @return
	 * @throws LimsException
	 */
	TestTemplateBM getTemplate( String patientTestId )throws LimsException;

}
