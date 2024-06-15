package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookingDAOEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BookingDAO extends JpaRepository<BookingDAOEntity, Integer>{

	@Query(nativeQuery = true, value = "select count(booking_id) from booking where date = :date and time_slot= :timeSlot and vaccination_centre = :vaccinationCentre")
	Long countBookingsBySlot(String date, String timeSlot, String vaccinationCentre);
	
	@Query(nativeQuery = true, value = "select count(booking_id) from booking where date = :date and dose = :dose and vaccination_centre = :vaccinationCentre")
	Long countBookingsByDateAndDose(String date, String dose, String vaccinationCentre);

	@Query(nativeQuery = true, value = "select * from booking where beneficiary_id = :beneficiaryId")
	List<BookingDAOEntity> retreiveData(@Param("beneficiaryId") String beneficiaryId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from booking where beneficiary_id = :beneficiaryId and dose = :dose")
	void deleteData(String beneficiaryId, String dose);
	

}
