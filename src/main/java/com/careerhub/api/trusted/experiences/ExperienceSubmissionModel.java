package com.careerhub.api.trusted.experiences;

import java.util.Date;

public class ExperienceSubmissionModel implements ExperienceSubmission {

	private String title;
	private String organisation;
	private String description;
	
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	
	private Date start;	
	private Date end;
	
	private Integer typeId;
	
	private Integer hoursId;

    @Override
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

    @Override
	public String getOrganisation() {
		return organisation;
	}
	
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

    @Override
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

    @Override
	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

    @Override
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

    @Override
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

    @Override
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

    @Override
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

    @Override
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

    @Override
	public Integer getHoursId() {
		return hoursId;
	}

	public void setHoursId(Integer hoursId) {
		this.hoursId = hoursId;
	}
}
