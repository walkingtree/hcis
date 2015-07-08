/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Rohit
 *
 */
public class RosterBM implements Cloneable, Serializable 
{
	private static final long serialVersionUID = -8757589144072770020L;

	private String		entityType;
	private Integer		entityId;
	private String 		entityFirstName;
	private String 		entityMiddleName;
	private String 		entityLastName;
	private List<RosterTimeBM> rosterTimeBMList;
// private String personId;
	private Date		fromDate;
	private Date		toDate;
	private String		period;
	private List<Integer> daysList;

	public List<Integer> getDaysList() {
		return daysList;
	}
	public void setDaysList(List<Integer> daysList) {
		this.daysList = daysList;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getEntityFirstName() {
		return entityFirstName;
	}
	public void setEntityFirstName(String entityFirstName) {
		this.entityFirstName = entityFirstName;
	}
	public String getEntityMiddleName() {
		return entityMiddleName;
	}
	public void setEntityMiddleName(String entityMiddleName) {
		this.entityMiddleName = entityMiddleName;
	}
	public String getEntityLastName() {
		return entityLastName;
	}
	public void setEntityLastName(String entityLastName) {
		this.entityLastName = entityLastName;
	}

	public List<RosterTimeBM> getRosterTimeBMList() {
		return rosterTimeBMList;
	}
	public void setRosterTimeBMList(List<RosterTimeBM> rosterTimeBMList) {
		this.rosterTimeBMList = rosterTimeBMList;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
}
