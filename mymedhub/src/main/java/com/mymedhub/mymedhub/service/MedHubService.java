package com.mymedhub.mymedhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedhub.mymedhub.entity.MedInfoDetail;
import com.mymedhub.mymedhub.entity.MedInfoMaster;
import com.mymedhub.mymedhub.repository.MedInfoDetailRepo;
import com.mymedhub.mymedhub.repository.MedInfoMasterRepo;

@Service
@Transactional
public class MedHubService {

	@Autowired
	private MedInfoMasterRepo medInfoMasterRepo;
	
	@Autowired
	private MedInfoDetailRepo medInfoDetailRepo;
	
	
	public void saveMedInfoMaster(MedInfoMaster medInfoMaster) {
		// TODO Auto-generated method stub
		medInfoMasterRepo.save(medInfoMaster);
		
	}

	public void saveMedInfoDetail(List<MedInfoDetail> ls) {
		// TODO Auto-generated method stub
		medInfoDetailRepo.saveAll(ls);
		
	}

	public Optional<MedInfoMaster> findByID(String id) {
		// TODO Auto-generated method stub
		return medInfoMasterRepo.findById(id);
	}

	public List<MedInfoDetail> getReminderTimes(String id) {
		// TODO Auto-generated method stub
		return medInfoDetailRepo.findByRFID(id);
	}
	
	public List<MedInfoDetail> getAllReminderTimes() {
		// TODO Auto-generated method stub
		return medInfoDetailRepo.findAll();
	}

}
