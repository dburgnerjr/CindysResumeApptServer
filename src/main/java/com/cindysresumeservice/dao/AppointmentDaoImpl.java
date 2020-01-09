package com.cindysresumeservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cindysresumeservice.entity.Appointment;

@Repository("appointmentDao")
@Transactional
public class AppointmentDaoImpl implements AppointmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean saveAppointment(Appointment appt) {
		getSession().persist(appt);
		if (isApptExist(appt)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Appointment> findAllAppts() {
		Criteria criteria = getSession().createCriteria(Appointment.class);
		List<Appointment> apptList = criteria.list();
		return criteria.list();
	}

	@Override
	public Integer deleteApptById(Long id) {
		Query query = getSession().createSQLQuery("delete from Appointment where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	@Override
	public Appointment findById(Long id) {
		Criteria criteria = getSession().createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("id", id));
		return (Appointment) criteria.uniqueResult();
	}

	@Override
	public Appointment findByName(String strN) {
		Criteria criteria = getSession().createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("name", strN));
		return (Appointment) criteria.uniqueResult();
	}

	@Override
	public boolean updateAppt(Appointment appt) {
		getSession().update(appt);
		Appointment apptUpd = findById(appt.getId());
		if (apptUpd == appt) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isApptExist(Appointment appt) {
		return findByName(appt.getName()) != null;
	}
}
