package in.wtc.hcis.bm.base;

import java.io.Serializable;

public class OtherDetailsBM implements Serializable {

	static final long serialVersionUID = 200904130621L;
	
	private boolean smokingHabitFlag;
	private boolean drinksAlcohol;
	
	public OtherDetailsBM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtherDetailsBM(boolean smokingHabitFlag, boolean drinksAlcohol,
			String fitnessActivityList) {
		super();
		this.smokingHabitFlag = smokingHabitFlag;
		this.drinksAlcohol = drinksAlcohol;
	}

	public boolean isSmokingHabitFlag() {
		return smokingHabitFlag;
	}

	public void setSmokingHabitFlag(boolean smokingHabitFlag) {
		this.smokingHabitFlag = smokingHabitFlag;
	}

	public boolean isDrinksAlcohol() {
		return drinksAlcohol;
	}

	public void setDrinksAlcohol(boolean drinksAlcohol) {
		this.drinksAlcohol = drinksAlcohol;
	}
}
