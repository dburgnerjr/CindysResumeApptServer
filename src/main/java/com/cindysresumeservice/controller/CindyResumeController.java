package com.cindysresumeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cindysresumeservice.entity.Appointment;
import com.cindysresumeservice.manager.ResumeManager;

/*
 * author: Daniel Burgner
 * 
 */

@Controller
public class CindyResumeController {

	@Autowired
	private ResumeManager resumeManager; // Service which will do all data retrieval/manipulation work

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String getHomePage() {
		return "index";
	}

	@RequestMapping(value = "/appointmentScheduler", method = RequestMethod.GET)
	public ModelAndView getAppointmentScheduler() {
		return new ModelAndView("appointmentScheduler", "appointments", resumeManager.findAllAppts());
	}

	// -------------------Retrieve All
	// Appointments--------------------------------------------------------
	@RequestMapping(value = "/appointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> listAllAppointments() {
		List<Appointment> appts = resumeManager.findAllAppts();
		if (appts.isEmpty()) {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Appointment>>(appts, HttpStatus.OK);
		}
	}

	// -------------------Retrieve Single
	// Appointment--------------------------------------------------------

	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> getAppointment(@PathVariable("id") Long id) {
		Appointment appt = resumeManager.findById(id);
		if (null == appt) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Appointment>(appt, HttpStatus.OK);
		}
	}

	// -------------------Create a
	// Appointment--------------------------------------------------------

	@RequestMapping(value = "/appointments/appointment", method = RequestMethod.POST)
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appt) {
		if (resumeManager.isApptExist(appt)) {
			return new ResponseEntity<Appointment>(HttpStatus.CONFLICT);
		} else if (resumeManager.saveAppt(appt)) {
			return new ResponseEntity<Appointment>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Appointment>(HttpStatus.NO_CONTENT);
		}
	}

	// ------------------- Update a Appointment
	// --------------------------------------------------------

	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appt) {
		Appointment currentAppointment = resumeManager.findById(id);

		if (currentAppointment == null) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		} else {
			currentAppointment.setName(appt.getName());
			currentAppointment.setDate(appt.getDate());
			currentAppointment.setEmail(appt.getEmail());
		}

		if (resumeManager.updateAppt(currentAppointment))
			return new ResponseEntity<Appointment>(currentAppointment, HttpStatus.OK);
		else
			return new ResponseEntity<Appointment>(currentAppointment, HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete a Appointment
	// --------------------------------------------------------

	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") Long id) {
		Integer nRowCount = resumeManager.deleteApptById(id);
		if (nRowCount == 1)
			return new ResponseEntity<Appointment>(HttpStatus.OK);
		else
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
	}
}
