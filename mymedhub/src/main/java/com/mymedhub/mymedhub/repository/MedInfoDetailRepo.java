package com.mymedhub.mymedhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mymedhub.mymedhub.entity.MedInfoDetail;

@Repository
public interface MedInfoDetailRepo extends JpaRepository<MedInfoDetail , String> {

	@Query(value = "SELECT m FROM MedInfoDetail m where m.medInfoMaster.rfid =:id")
	public List<MedInfoDetail> findByRFID(@Param("id") String id);
}
