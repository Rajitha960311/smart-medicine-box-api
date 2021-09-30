package com.mymedhub.mymedhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="med_info_detail")
public class MedInfoDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@ManyToOne(optional = true,fetch = FetchType.EAGER)
	@JoinColumn(name = "rfid", referencedColumnName = "rfid")
	private MedInfoMaster medInfoMaster;
	
	@Column(name="time")
	private String time;
	
	@Column(name="dosage")
	private int dosage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MedInfoMaster getMedInfoMaster() {
		return medInfoMaster;
	}

	public void setMedInfoMaster(MedInfoMaster medInfoMaster) {
		this.medInfoMaster = medInfoMaster;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public MedInfoDetail(String id, MedInfoMaster medInfoMaster, String time, int dosage) {
		super();
		this.id = id;
		this.medInfoMaster = medInfoMaster;
		this.time = time;
		this.dosage = dosage;
	}

	public MedInfoDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
