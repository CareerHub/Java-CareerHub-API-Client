package com.careerhub.api.trusted.experiences;

import java.util.Date;

public final class ExperienceModel {
	private int id;
	private String title;
	private String organisation;
	private String description;
	
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	
	private Date start;
	private Date startUtc;
	
	private Date end;
	private Date endUtc;
	
	private Integer typeId;
	private String type;
	
	private Integer hoursId;
	private String hours;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOrganisation() {
		return organisation;
	}
	
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStartUtc() {
		return startUtc;
	}

	public void setStartUtc(Date startUtc) {
		this.startUtc = startUtc;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getEndUtc() {
		return endUtc;
	}

	public void setEndUtc(Date endUtc) {
		this.endUtc = endUtc;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getHoursId() {
		return hoursId;
	}

	public void setHoursId(Integer hoursId) {
		this.hoursId = hoursId;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
}
