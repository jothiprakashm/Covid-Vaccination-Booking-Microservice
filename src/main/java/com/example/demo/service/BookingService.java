package com.example.demo.service;

import com.example.demo.domain.CancellationInput;
import com.example.demo.domain.PersonBookingInput;
import com.example.demo.domain.ReschedulingInput;

public interface BookingService {
	
	public int book(PersonBookingInput personBookingInput) throws Exception;
	public boolean validateDateDoseCentre(PersonBookingInput personBookingInput) throws Exception;
	public boolean validateSlot(PersonBookingInput personBookingInput) throws Exception;
	public boolean validateDate(PersonBookingInput personBookingInput) throws Exception;
	public boolean validateDose(PersonBookingInput personBookingInput) throws Exception;
	public boolean validateDoseInterval(PersonBookingInput personBookingInput) throws Exception;
	public void cancel(CancellationInput cancellationInput) throws Exception;
	public boolean validateReschedulingDateDoseCentre(ReschedulingInput reschedulingInput) throws Exception;
	public boolean validateReschedulingSlot(ReschedulingInput reschedulingInput) throws Exception;
	public boolean validateReschedulingDate(ReschedulingInput reschedulingInput) throws Exception;
	public int reschedule(ReschedulingInput reschedulingInput) throws Exception;
}
