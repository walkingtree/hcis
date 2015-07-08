/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import com.wtc.hcis.ip.da.BedEnvelopeHasPoolDAO;

/**
 * @author Alok Ranjan
 *
 */
public class BedEnvelopeHasPoolDAOExtn extends BedEnvelopeHasPoolDAO {
	public boolean deletePoolsOfEnvelope( String envelopeName ) {
		this.getHibernateTemplate().deleteAll( this.findByProperty("id.envelopeName", envelopeName ) );
		
		return true;
	}
	
}
