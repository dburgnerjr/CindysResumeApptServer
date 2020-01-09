package com.cindysresumeservice.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindysresumeservice.entity.Appointment;
import com.cindysresumeservice.service.ResumeService;

@Service
public class ResumeManagerImpl implements ResumeManager {
	@Autowired
	private ResumeService resumeService;

	@Override
	public Appointment findById(Long id) {
		return resumeService.findById(id);
	}

	@Override
	public boolean saveAppt(Appointment appt) {
		return resumeService.saveAppt(appt);
	}

	@Override
	public boolean updateAppt(Appointment appt) {
		return resumeService.updateAppt(appt);
	}

	@Override
	public Integer deleteApptById(Long id) {
		return resumeService.deleteApptById(id);
	}

	@Override
	public boolean isApptExist(Appointment appt) {
		return resumeService.isApptExist(appt);
	}

	@Override
	public List<Appointment> findAllAppts() {
		return resumeService.findAllAppts();
	}
}
