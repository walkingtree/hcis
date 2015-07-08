/**
 * 
 */
package in.wtc.hcis.bo.report;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ReportParamBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ReferenceDataConstants;
import in.wtc.hcis.bo.constants.ReportsDetail;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReportParam;
import com.wtc.hcis.da.extn.ReportParamDAOExtn;
import com.wtc.report.ReportManager;
import com.wtc.report.model.ReportDetail;


/**
 * @author Bhavesh
 *
 */
public class CoreReportManagerImpl implements CoreReportManager,ApplicationContextAware {

	private ReportParamDAOExtn reportParamDAO;
	private CommonDataManager commonDataManager;
	private ReportManager reportManager; 
	
	private ApplicationContext applicationContext;
	
	private ResourceBundle reportsConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.ReportDetail", Locale.getDefault());

	public ListRange getReportWidgets( String reportCode ,int start, int count, String orderBy ) throws HCISException{
		
		try {
			List<ReportParamBM> reportParamBMList = new ArrayList<ReportParamBM>();
			if (reportCode != null && !reportCode.isEmpty()) {

				List<ReportParam> reportParamList = reportParamDAO.getOrderedReportParams(reportCode);
				if (reportParamList != null	&& !reportParamList.isEmpty()) {

					for (ReportParam reportParam : reportParamList) {

						ReportParamBM reportParamBM = new ReportParamBM();

						ReferenceDataList referenceData = commonDataManager.getReferenceData(ReferenceDataConstants.REPORT,
																							 reportCode);
						CodeAndDescription reportName = new CodeAndDescription();
						
						reportName.setCode(referenceData.getId().getAttrCode());
						reportName.setDescription( referenceData.getAttrDesc() );
						
						reportParamBM.setReportName( reportName );
						reportParamBM.setWidgetName(reportParam.getId().getWidgetName());
						reportParamBM.setXtype(reportParam.getXtype());
						reportParamBM.setWidgetLabel( reportParam.getWidgetLabel());
						reportParamBM.setWidgetLength( reportParam.getWidgetLength());
						reportParamBM.setWidgetSeqNbr( reportParam.getWidgetLength() );
						reportParamBM.setRequiredFlag( reportParam.getRequiredFlag() );
							
							
						if( reportParam.getXtype()!= null && reportParam.getXtype().equals( CoreReportConstants.WIDGET_XTYPE_MVL) ){

							String dataProvide = reportParam.getDataProviderMethod();
							
							if( dataProvide != null && !dataProvide.isEmpty()){
//			Use reflexion to invoke the method 
//			Here we have assumption that 'provider' contains QualifiedName (without argument list as it always should be
//			int,int,String) of the	provider method and bean name in the Spring configuration file is same as the class name.
								
								int lastIndex = dataProvide.lastIndexOf(".");
								String className = dataProvide.substring(0, lastIndex);
								String methodName = dataProvide.substring( lastIndex +1 );
								
								try {
									 Class cl=Class.forName( className );
									 Class[] param = new Class[3];
									 param[0] = int.class;
									 param[1] = int.class;
									 param[2] = String.class;
								
									  Method method = cl.getMethod( methodName , param);
									
									  String[] beanNames  = applicationContext.getBeanNamesForType( cl );
									  
									  
									  if( beanNames != null  && beanNames[0] != null ){
										  
										  Object obj = applicationContext.getBean(beanNames[0]);
										  ListRange listRange = (ListRange) method.invoke(obj ,0,999,"");
										  Object[] obje = (Object[]) listRange.getData();

										  reportParamBM.setMVLvalueList(new ArrayList(Arrays.asList( listRange.getData() )));
									  }else{
										  
										  throw new Exception("Spring Bean with for class  '" +className
												  			   +"' is not defined.");
									  }
									  
								} catch (Exception e) {
									Fault fault = new Fault( ApplicationErrors.READ_ATTRIBUTE_FAILED );
									HCISException hcisException = new HCISException(fault, e);
									throw hcisException;
								}
							}
						}
						reportParamBMList.add(reportParamBM);
					}
				}
			}
			
			
			ListRange listRange = new ListRange();
			
			List<ReportParamBM> pageSizeData = new ArrayList<ReportParamBM>();
			int index = 0;
			if (reportParamBMList != null && reportParamBMList.size() > 0) {
			while( (start+index < start + count) && (reportParamBMList.size() > start+index) ){
				
				ReportParamBM reportParamBM = reportParamBMList.get(start+index);
				pageSizeData.add( reportParamBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(reportParamBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ATTRIBUTE_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	public String generateReport( String reportCode, Map<String,Object> paramNameValueMap ){
		
		try {
			if( reportCode != null && !reportCode.isEmpty() ){
				String reportFileName = reportsConfig.getString( reportCode );
				
				ReportDetail reportDetail = new ReportDetail();
				
				reportDetail.setFileName(reportFileName);
				reportDetail.setName(reportCode);
				reportDetail.setReportsDirPath(reportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
				
				Set keySet = paramNameValueMap.keySet();
				Iterator iter = keySet.iterator();
				while(iter.hasNext()){
					String key = iter.next().toString();
					if(paramNameValueMap.get(key).equals("undefined")){
						paramNameValueMap.put(key, "");
					}
				}
				
				String reportUrl = reportManager.generateReport(reportDetail,
								   paramNameValueMap,ReportsDetail.REPORT_DATASORCE_SESSION);
				
				return reportUrl;
			}
			return null;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.ERROR_RUNNING_REPORT );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {

		this.applicationContext = ctx;
		
	}

	public void setReportParamDAO(ReportParamDAOExtn reportParamDAO) {
		this.reportParamDAO = reportParamDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public void setReportsConfig(ResourceBundle reportsConfig) {
		this.reportsConfig = reportsConfig;
	}

}
