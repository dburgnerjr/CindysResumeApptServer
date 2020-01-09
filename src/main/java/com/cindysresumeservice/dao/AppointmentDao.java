package com.cindysresumeservice.dao;

import java.util.List;

import com.cindysresumeservice.entity.Appointment;

public interface AppointmentDao {
	boolean saveAppointment(Appointment appt);

	List<Appointment> findAllAppts();

	Integer deleteApptById(Long id);

	Appointment findById(Long id);

	Appointment findByName(String strN);

	boolean isApptExist(Appointment appt);

	boolean updateAppt(Appointment appt);
}
