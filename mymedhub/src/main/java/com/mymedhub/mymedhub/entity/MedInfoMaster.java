package com.mymedhub.mymedhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="med_info_master")
public class MedInfoMaster {

	@Id
	@Column(name="rfid")
	private String rfid;
	
	@Column(name="med_name")
	private String medName;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="intake_advice")
	private String intakeAdvice;
	
	@Column(name="inventory")
	private int inventory;
	
	@Column(name="as_needed")
	private boolean asNeeded;

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIntakeAdvice() {
		return intakeAdvice;
	}

	public void setIntakeAdvice(String intakeAdvice) {
		this.intakeAdvice = intakeAdvice;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public boolean isAsNeeded() {
		return asNeeded;
	}

	public void setAsNeeded(boolean asNeeded) {
		this.asNeeded = asNeeded;
	}

	public MedInfoMaster(String rfid, String medName, String unit, String intakeAdvice, int inventory,
			boolean asNeeded) {
		super();
		this.rfid = rfid;
		this.medName = medName;
		this.unit = unit;
		this.intakeAdvice = intakeAdvice;
		this.inventory = inventory;
		this.asNeeded = asNeeded;
	}

	public MedInfoMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
