package com.cindysresumeservice.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cindysresumeservice.entity.Appointment;
import com.cindysresumeservice.manager.ResumeManager;

@Controller
public class CindyResumeCommentController {

	@Autowired
	private ResumeManager resumeManager; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve Single
	// Appointment--------------------------------------------------------
	@RequestMapping(value = "/updateComment/{id}", method = RequestMethod.GET)
	public ModelAndView updateComment(@PathVariable("id") Long id) {
		Appointment appt = resumeManager.findById(id);

		String strDate = "";
		DateFormat dfDateTgt = new SimpleDateFormat("MM/dd/yyyy");
		strDate = dfDateTgt.format(appt.getDate());

		ModelAndView mavView = new ModelAndView();
		mavView.setViewName("updateComment");

		mavView.addObject("name", appt.getName());
		mavView.addObject("date", strDate);
		mavView.addObject("email", appt.getEmail());
		mavView.addObject("comments", appt.getComments());

		return mavView;
	}

	@RequestMapping(value = "/submitUpdatedComment", method = RequestMethod.POST)
	public ModelAndView submitUpdatedComment(HttpServletRequest req) {
		Appointment appt = new Appointment();
		Date dtDate = null;

		Long lId = Long.valueOf(req.getParameter("id"));
		String strName = req.getParameter("name");
		String strDate = req.getParameter("date");
		String strEmail = req.getParameter("email");
		String strComments = req.getParameter("comments");

		ModelAndView mavView = new ModelAndView();
		mavView.setViewName("submitUpdatedComment");

		appt.setId(lId);
		appt.setName(strName);

		try {
			dtDate = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
			appt.setDate(dtDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		appt.setEmail(strEmail);
		appt.setComments(strComments);

		mavView.addObject("name", appt.getName());
		mavView.addObject("date", strDate);
		mavView.addObject("email", appt.getEmail());
		mavView.addObject("comments", appt.getComments());

		resumeManager.updateAppt(appt);
		return mavView;
	}
}
