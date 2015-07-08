package com.wtc.hcis.da;

/**
 * DateDim entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DateDim implements java.io.Serializable {

	// Fields

	private Integer dateId;
	private Integer dayOfYear;
	private Integer year;
	private Integer month;
	private Integer yearQuarter;
	private Integer yearAndMonth;
	private Integer yearMonthWeek;
	private Integer yearMonthDate;
	private Integer dayOfMonth;
	private Integer weekNbr;
	private Integer quarterNbr;
	private String dayName;
	private String monthName;

	// Constructors

	/** default constructor */
	public DateDim() {
	}

	/** full constructor */
	public DateDim(Integer dayOfYear, Integer year, Integer month,
			Integer yearQuarter, Integer yearAndMonth, Integer yearMonthWeek,
			Integer yearMonthDate, Integer dayOfMonth, Integer weekNbr,
			Integer quarterNbr, String dayName, String monthName) {
		this.dayOfYear = dayOfYear;
		this.year = year;
		this.month = month;
		this.yearQuarter = yearQuarter;
		this.yearAndMonth = yearAndMonth;
		this.yearMonthWeek = yearMonthWeek;
		this.yearMonthDate = yearMonthDate;
		this.dayOfMonth = dayOfMonth;
		this.weekNbr = weekNbr;
		this.quarterNbr = quarterNbr;
		this.dayName = dayName;
		this.monthName = monthName;
	}

	// Property accessors

	public Integer getDateId() {
		return this.dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public Integer getDayOfYear() {
		return this.dayOfYear;
	}

	public void setDayOfYear(Integer dayOfYear) {
		this.dayOfYear = dayOfYear;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYearQuarter() {
		return this.yearQuarter;
	}

	public void setYearQuarter(Integer yearQuarter) {
		this.yearQuarter = yearQuarter;
	}

	public Integer getYearAndMonth() {
		return this.yearAndMonth;
	}

	public void setYearAndMonth(Integer yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public Integer getYearMonthWeek() {
		return this.yearMonthWeek;
	}

	public void setYearMonthWeek(Integer yearMonthWeek) {
		this.yearMonthWeek = yearMonthWeek;
	}

	public Integer getYearMonthDate() {
		return this.yearMonthDate;
	}

	public void setYearMonthDate(Integer yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}

	public Integer getDayOfMonth() {
		return this.dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getWeekNbr() {
		return this.weekNbr;
	}

	public void setWeekNbr(Integer weekNbr) {
		this.weekNbr = weekNbr;
	}

	public Integer getQuarterNbr() {
		return this.quarterNbr;
	}

	public void setQuarterNbr(Integer quarterNbr) {
		this.quarterNbr = quarterNbr;
	}

	public String getDayName() {
		return this.dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getMonthName() {
		return this.monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

}