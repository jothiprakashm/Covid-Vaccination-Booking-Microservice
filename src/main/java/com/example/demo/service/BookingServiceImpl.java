package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.CancellationInput;
import com.example.demo.domain.PersonBookingInput;
import com.example.demo.domain.ReschedulingInput;
import com.example.demo.entity.BookingDAOEntity;
import com.example.demo.repository.BookingDAO;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public int book(PersonBookingInput personBookingInput) throws Exception {
		BookingDAOEntity response = null;
		int id = 0;
		try {
			response =  bookingDAO.save(this.modelMapper.map(personBookingInput, BookingDAOEntity.class));
			id = response.getBookingId();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}

	@Override
	public void cancel(CancellationInput cancellationInput) throws Exception {
		try {
			bookingDAO.deleteData(cancellationInput.getBeneficiaryId(), cancellationInput.getDose());
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public int reschedule(ReschedulingInput reschedulingInput) throws Exception {
		int id = 0;
		try {
			BookingDAOEntity response = bookingDAO.save(this.modelMapper.map(reschedulingInput, BookingDAOEntity.class));
			id = response.getBookingId();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}

	@Override
	public boolean validateDateDoseCentre(PersonBookingInput personBookingInput) throws Exception {
		long countOfBookingsByDateAndDose = bookingDAO.countBookingsByDateAndDose(personBookingInput.getDate(), personBookingInput.getDose(), personBookingInput.getVaccinationCentre());
		if(countOfBookingsByDateAndDose < 15)
			return true;
		return false;
	}

	@Override
	public boolean validateSlot(PersonBookingInput personBookingInput) throws Exception {
		long countOfBookingsBySlot = bookingDAO.countBookingsBySlot(personBookingInput.getDate(), personBookingInput.getTimeSlot() , personBookingInput.getVaccinationCentre());
		if(countOfBookingsBySlot < 10)
			return true;
		return false;
	}

	@Override
	public boolean validateDate(PersonBookingInput personBookingInput) throws Exception {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate bookingDate = LocalDate.parse(personBookingInput.getDate() , formatter);
		long noOfDays = ChronoUnit.DAYS.between(today, bookingDate);
		if(noOfDays < 90)
			return true;
		return false;
	}

	@Override
	public boolean validateDose(PersonBookingInput personBookingInput) throws Exception {
		int flag = 1;
		List<BookingDAOEntity> result = bookingDAO.retreiveData(personBookingInput.getBeneficiaryId());
		if(!result.isEmpty()) {	
			for (BookingDAOEntity a : result) {
		    	if(a.getDose().equals("First")) {
		    		if(personBookingInput.getDose().equals("First")) {
		    			flag = 0;
		    		}
		    	}
		    	if(a.getDose().equals("Second")) {
		    		if(personBookingInput.getDose().equals("Second")) {
		    			flag = 0;
		    		}
		    	}
			}
		}
		if(flag == 1)
			return true;
		return false;
	}

	@Override
	public boolean validateReschedulingDateDoseCentre(ReschedulingInput reschedulingInput) throws Exception {
		long countOfBookingsByDateAndDose = bookingDAO.countBookingsByDateAndDose(reschedulingInput.getDate(), reschedulingInput.getDose(), reschedulingInput.getVaccinationCentre());
		if(countOfBookingsByDateAndDose < 15)
			return true;
		return false;
	}

	@Override
	public boolean validateReschedulingSlot(ReschedulingInput reschedulingInput) throws Exception {
		long countOfBookingsBySlot = bookingDAO.countBookingsBySlot(reschedulingInput.getDate(), reschedulingInput.getTimeSlot() , reschedulingInput.getVaccinationCentre());
		if(countOfBookingsBySlot < 10)
			return true;
		return false;
	}

	@Override
	public boolean validateReschedulingDate(ReschedulingInput reschedulingInput) throws Exception {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate bookingDate = LocalDate.parse(reschedulingInput.getDate() , formatter);
		long noOfDays = ChronoUnit.DAYS.between(today, bookingDate);
		if(noOfDays < 90)
			return true;
		return false;
	}

	@Override
	public boolean validateDoseInterval(PersonBookingInput personBookingInput) throws Exception {
		int flag = 1;
		List<BookingDAOEntity> result = bookingDAO.retreiveData(personBookingInput.getBeneficiaryId());
		if(!result.isEmpty()) {	
			for (BookingDAOEntity a : result) {
				if(a.getDose().equals("First") && personBookingInput.getDose().equals("Second")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate bookingDate = LocalDate.parse(personBookingInput.getDate() , formatter);
					LocalDate firstDoseDate = LocalDate.parse(a.getDate() , formatter);
					long noOfDays = ChronoUnit.DAYS.between(firstDoseDate, bookingDate);
					if(noOfDays < 15) {
						flag = 0;
					}
				}
			}
		}
		if(flag == 1)
			return true;
		return false;
	}
	

}
