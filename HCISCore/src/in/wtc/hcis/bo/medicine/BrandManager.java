/**
 * 
 */
package in.wtc.hcis.bo.medicine;

import java.util.List;

import in.wtc.hcis.bm.base.BrandBM;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Rohit
 *
 */
public interface BrandManager 
{
	void addBrand( BrandBM brandBM ) throws HCISException;
	
	BrandBM modifyBrand( BrandBM brandBM ) throws HCISException;
	
	void removeBrand( List<String> brandCodeList ) throws HCISException;
	
	List<BrandBM> getBrand( String brandCode,
							String description,
							Boolean active) throws HCISException;
	
	boolean isBrandExist( String brandCode );
}
