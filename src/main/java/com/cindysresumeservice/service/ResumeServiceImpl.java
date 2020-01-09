package com.cindysresumeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindysresumeservice.dao.AppointmentDao;
import com.cindysresumeservice.entity.Appointment;

@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private AppointmentDao dao;

	@Override
	public List<Appointment> findAllAppts() {
		return dao.findAllAppts();
	}

	@Override
	public Appointment findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public boolean saveAppt(Appointment appt) {
		return dao.saveAppointment(appt);
	}

	@Override
	public boolean updateAppt(Appointment appt) {
		return dao.updateAppt(appt);
	}

	@Override
	public Integer deleteApptById(Long id) {
		return dao.deleteApptById(id);
	}

	@Override
	public boolean isApptExist(Appointment appt) {
		return findById(appt.getId()) != null;
	}
}
