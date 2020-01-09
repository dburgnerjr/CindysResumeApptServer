package com.cindysresumeservice.manager;

import java.util.List;

import com.cindysresumeservice.entity.Appointment;

public interface ResumeManager {
	Appointment findById(Long id);

	boolean saveAppt(Appointment appt);

	boolean updateAppt(Appointment appt);

	Integer deleteApptById(Long id);

	List<Appointment> findAllAppts();

	boolean isApptExist(Appointment appt);
}
