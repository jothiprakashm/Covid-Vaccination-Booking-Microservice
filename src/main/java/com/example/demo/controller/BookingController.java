package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.constants.BookingConstants;
import com.example.demo.domain.CancellationInput;
import com.example.demo.domain.PersonBookingInput;
import com.example.demo.domain.ReschedulingInput;
import com.example.demo.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processBooking(@RequestBody PersonBookingInput personBookingInput) {
		String response = null;
		int id = 0;
		try {
			if(bookingService.validateDate(personBookingInput)) {
				if(bookingService.validateSlot(personBookingInput)) {
					if(bookingService.validateDateDoseCentre(personBookingInput)) {
						if(bookingService.validateDose(personBookingInput)) {
							if(bookingService.validateDoseInterval(personBookingInput)) {
								id = bookingService.book(personBookingInput);
								response = BookingConstants.SUCCESS + id;
							}
							else {
								response = BookingConstants.INVALID_DOSE_INTERVAL;
							}
						}
						else {
							response = BookingConstants.DOSE_ALREADY_BOOKED;
						}
					}
					else {
						response = BookingConstants.CENTRE_FULL;
					}
				}
				else {
					response = BookingConstants.INVALID_SLOT;
				}
			}
			else {
				response = BookingConstants.INVALID_DATE;
			}	
		}
		catch(Exception e) {
			response = BookingConstants.FAILURE + e.getMessage();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processCancellation(@RequestBody CancellationInput cancellationInput) {
		String response = null;
		try {
			bookingService.cancel(cancellationInput);
			response = BookingConstants.CANCELLATION_SUCCESS;
		} catch (Exception e) {
			response = BookingConstants.FAILURE;
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = "/reschedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processRescheduling(@RequestBody ReschedulingInput reschedulingInput) {
		String response = null;
		int id = 0;
		try {
			if(bookingService.validateReschedulingDate(reschedulingInput)) {
				if(bookingService.validateReschedulingSlot(reschedulingInput)) {
					if(bookingService.validateReschedulingDateDoseCentre(reschedulingInput)) {
						id = bookingService.reschedule(reschedulingInput);
						response = BookingConstants.RESCHEDULE_SUCCESS + id;
					}
					else {
						response = BookingConstants.CENTRE_FULL;
					}
				}
				else {
					response = BookingConstants.INVALID_SLOT;
				}
			}
			else {
				response = BookingConstants.INVALID_DATE;
			}	
		}
		catch(Exception e) {
			response = BookingConstants.FAILURE;
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
