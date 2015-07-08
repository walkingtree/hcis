package com.wtc.hcis.ip.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.BedEnvelopeDAO;

public class BedEnvelopeDAOExtn extends BedEnvelopeDAO {
	public List<BedEnvelope> findBedEnvelopes( String envelopeName,
											   String facilityTypeInd,
											   String poolName,
											   Date effectiveFrom,
											   Date effectiveTo) {
		
		StringBuffer hqlQueryString = new StringBuffer("from BedEnvelope as be left join be.bedEnvelopeHasPools as behp");
		
		boolean whereFlag = true;
		String whereStr = "";
		String andStr = "";
		
		if (envelopeName != null && envelopeName.length() > 0) {
			whereStr = " where ";
			hqlQueryString.append(whereStr + " be.envelopeName like ('%" + envelopeName.toLowerCase() + "%')");
			andStr = " and "; whereStr = ""; whereFlag = false;
		}

		if (facilityTypeInd != null && facilityTypeInd.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr +" be.facilityTypeInd='" + facilityTypeInd + "'");
			andStr = " and "; whereStr = "";
		}
		
		if (poolName != null && poolName.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " behp.id.poolName='" + poolName + "'");
			andStr = " and "; whereStr = "";
		}
		
		if( effectiveFrom != null ){
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr + " behp.effectiveFromDt <= :effectiveFrom ");
			andStr = " and "; whereStr = "";
			
		}
		if( effectiveTo != null){
			
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr + " behp.effectiveToDt >= :effectiveTo ");
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
		
		//**
		List bedEnvelopIdList = new ArrayList();

		Iterator<Object> iterator = resultList.iterator();
		while( iterator.hasNext() ) {
			Object [] objArray = (Object[])iterator.next();
			BedEnvelope bedEnvelop = (BedEnvelope)objArray[0];
			if(newList.size() == 0){
					newList.add(bedEnvelop);
					bedEnvelopIdList.add(bedEnvelop.getEnvelopeName());
			}else{
				if( ! bedEnvelopIdList.contains(bedEnvelop.getEnvelopeName())){
					newList.add(bedEnvelop);
					bedEnvelopIdList.add(bedEnvelop.getEnvelopeName());
				}
			}
		}
//		**
		
		return newList;
	}

}
