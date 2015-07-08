package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Room entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Room implements java.io.Serializable {

	// Fields

	private String roomCode;
	private String description;
	private Short active;
	private Set doctorEspecialties = new HashSet(0);
	private Set rosters = new HashSet(0);

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(String description, Short active, Set doctorEspecialties,
			Set rosters) {
		this.description = description;
		this.active = active;
		this.doctorEspecialties = doctorEspecialties;
		this.rosters = rosters;
	}

	// Property accessors

	public String getRoomCode() {
		return this.roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getDoctorEspecialties() {
		return this.doctorEspecialties;
	}

	public void setDoctorEspecialties(Set doctorEspecialties) {
		this.doctorEspecialties = doctorEspecialties;
	}

	public Set getRosters() {
		return this.rosters;
	}

	public void setRosters(Set rosters) {
		this.rosters = rosters;
	}

}