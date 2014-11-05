package com.careerhub.api.trusted.experiences;

import java.util.Date;

public interface ExperienceSubmission {
	
	public String getTitle();
	public String getOrganisation();
	public String getDescription();

	public String getContactName();
	public String getContactEmail();
	public String getContactPhone();

	public Date getStart();
	public Date getEnd();

	public Integer getTypeId();
	public Integer getHoursId();
}
