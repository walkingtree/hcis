package com.wtc.hcis.ip.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedPoolDAO;

public class BedPoolDAOExtn extends BedPoolDAO {

	public List<BedPool> findBedPools(String poolName, 
									  String nursingUnitTypeCd, 
									  Date effectiveFrom, 
									  Date effectiveTo) {
		
		StringBuffer hqlQueryString = new StringBuffer("from BedPool as bp left join bp.bedPoolHasUnitTypes bphut");
		
		boolean whereFlag = true;
		String whereStr = "";
		String andStr = "";
		
		if (poolName != null && poolName.length() > 0) {
			whereStr = " where ";
			hqlQueryString.append(whereStr + " bp.bedPoolName like ('%" + poolName.toLowerCase() + "%')");
			andStr = " and "; whereStr = ""; whereFlag = false;
		}
		
		if (nursingUnitTypeCd != null && nursingUnitTypeCd.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr +" bphut.nursingUnitType.unitTypeCd='" + nursingUnitTypeCd + "'");
			andStr = " and "; whereStr = "";
		}
		if( effectiveFrom != null ){
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr + " bphut.effectiveFromDt <= :effectiveFrom ");
			andStr = " and "; whereStr = "";
			
		}
		if( effectiveTo != null){
			
			if (whereFlag) {
				whereStr = " where ";
			}
			hqlQueryString.append( whereStr + andStr + " bphut.effectiveToDate >= :effectiveTo ");
		}
		
		
		Query query = getSession().createQuery(hqlQueryString.toString());
		
		if( effectiveFrom != null ){
			query.setDate( "effectiveFrom", effectiveFrom );
		}
		if( effectiveTo != null){
			query.setDate( "effectiveTo", effectiveTo );
		}
		
		List resultList = query.list();
		List newList = new ArrayList() ;
		List bedPoolIdList = new ArrayList();
		
		Iterator<Object> iterator = resultList.iterator();
		while( iterator.hasNext() ) {
			Object [] objArray = (Object[])iterator.next();
			BedPool bedPool = (BedPool)objArray[0];
			if(newList.size() == 0){
				newList.add(bedPool);
				bedPoolIdList.add(bedPool.getBedPoolName());
			}else{
				if( ! bedPoolIdList.contains(bedPool.getBedPoolName())){
				newList.add(bedPool);
				bedPoolIdList.add(bedPool.getBedPoolName());
				}
			}
		}
		return newList;
	}
}
