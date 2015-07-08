/**
 * 
 */
package in.wtc.ui.controller;

import java.util.ArrayList;
import java.util.List;

import in.wtc.hcis.bm.base.BrandBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.medicine.BrandManager;

/**
 * @author Vinay Kurudi
 *
 */
public class BrandManagementController {

	private BrandManager brandManager;
	
	
	public void addBrand(BrandBM brandBM) {
		
		brandManager.addBrand(brandBM);
	}
	
	public void modifyBrand(BrandBM brandBM) {
		
		brandManager.modifyBrand(brandBM);
	}
	public void removeBrands(List<String> brandCodeList) {
		
		brandManager.removeBrand(brandCodeList);
	}
	
	public ListRange getBrands(String brandCode, String description, Boolean active, int start, int count, String orderBy) {
		ListRange listRange = new ListRange();

		List<BrandBM> brandList = brandManager.getBrand(brandCode, description, active);
			List<BrandBM> pageSizeData = new ArrayList<BrandBM>();
			if(brandList!=null && brandList.size()>0) {
				int index = 0;
				while( (start+index < start + count) && (brandList.size() > start+index) ){
						pageSizeData.add(brandList.get(start+index));
						index++;
				}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(brandList.size());
			}else {
				  Object[] brandSummaryObj = new Object[0] ;
				  listRange.setData(brandSummaryObj);
				  listRange.setTotalSize(brandSummaryObj.length);
			}
			
		return listRange;
	}

	
	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}
	public boolean isBrandExist(String brandCode){
		return brandManager.isBrandExist(brandCode);
	}
}
