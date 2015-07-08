/**
 * 
 */
package in.wtc.hcis.bo.medicine;

import java.util.ArrayList;
import java.util.List;

import com.wtc.hcis.da.Brand;
import com.wtc.hcis.da.extn.BrandDAOExtn;

import in.wtc.hcis.bm.base.BrandBM;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Rohit
 *
 */
public class BrandManagerImpl implements BrandManager 
{
	BrandDAOExtn brandDAOExtn;
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.medicine.BrandManager#addBrand(in.wtc.hcis.bm.base.BrandBM)
	 */
	public void addBrand( BrandBM brandBM ) throws HCISException
	{
		try
		{
			Brand brandDM = convertBrandBM2DM( brandBM, null );
			
			brandDAOExtn.save( brandDM );
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.BRAND_ADD_FAILED );
			HCISException  hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}
	

	public BrandBM modifyBrand(BrandBM modifiedBrandBM) throws HCISException 
	{
		try
		{
			Brand brand = this.getBrand( modifiedBrandBM.getBrandCode() );
			Brand brandDM = convertBrandBM2DM( modifiedBrandBM, brand );
			
			brandDAOExtn.attachDirty( brandDM );
			
			BrandBM brandBM = convertBrandDM2BM( brandDM );
			return brandBM;
		}catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.BRAND_MODIFY_FAILED );
			HCISException  hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}


	public void removeBrand( List<String> brandCodeList ) throws HCISException
	{
		try
		{
			for( String tmpId : brandCodeList ) {
				Brand brandDM = brandDAOExtn.findById( tmpId );
				brandDM.setActive( new Short( "0" ) );
				brandDAOExtn.attachDirty( brandDM );
			}
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.BRAND_REMOVE_FAILED );
			HCISException hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}

	
	public List<BrandBM> getBrand(	String brandCode, 
									String description,
									Boolean active) throws HCISException 
	{
		
		List<Brand> brandList = brandDAOExtn.getBrand(	brandCode, 
														description, 
														active);
		List<BrandBM> brandBMList = new ArrayList<BrandBM>();
		if( brandList != null && !brandList.isEmpty() ) {
			for( Brand tmpBrandDM : brandList ) {
				BrandBM tmpBrandBM = convertBrandDM2BM(tmpBrandDM);
				brandBMList.add(tmpBrandBM);
			}
		}
		return brandBMList;
	}

	
	public boolean isBrandExist( String brandCode ){
		
		if( brandCode != null && !brandCode.isEmpty()){
			Brand brand = brandDAOExtn.findById( brandCode );
			
			if(brand != null ){
				return true;
			}
		}
		return false;
	}
	
	private Brand convertBrandBM2DM( BrandBM brandBM, Brand brandDM )
	{
		if( brandDM == null ){
			 brandDM = new Brand();
		}
		if( brandBM != null ) {
			brandDM.setBrandCode( brandBM.getBrandCode() );
			brandDM.setDescription( brandBM.getDescription() );
			brandDM.setActive( new Short( brandBM.getActive()?"1":"0" ) );
		}
		return brandDM;
	}
	
	private BrandBM convertBrandDM2BM( Brand brandDM )
	{
		BrandBM brandBM = new BrandBM();
		if( brandDM != null ) {
			brandBM.setBrandCode( brandDM.getBrandCode() );
			brandBM.setDescription( brandDM.getDescription() );
			brandBM.setActive( brandDM.getActive() > 0? true : false );
		}
		return brandBM;
	}


	private Brand getBrand( String brandCode ) throws HCISException{
		try {
			Brand brand = brandDAOExtn.findById( brandCode );
			if( brand == null){
				new Exception("Brand with name "+brandCode +" does not exist");
			}
			return brand;
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.BRAND_READ_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	public void setBrandDAOExtn(BrandDAOExtn brandDAOExtn) {
		this.brandDAOExtn = brandDAOExtn;
	}
}
