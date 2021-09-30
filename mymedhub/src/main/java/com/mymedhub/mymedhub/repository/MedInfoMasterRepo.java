package com.mymedhub.mymedhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymedhub.mymedhub.entity.MedInfoMaster;

@Repository
public interface MedInfoMasterRepo extends JpaRepository<MedInfoMaster , String>{

}
