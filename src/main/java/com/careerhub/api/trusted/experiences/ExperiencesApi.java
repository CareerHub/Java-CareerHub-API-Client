package com.careerhub.api.trusted.experiences;

import java.io.IOException;

public interface ExperiencesApi extends AutoCloseable {
	public ExperienceModel[] GetExperiences(String studentId) throws IOException;
	public ExperienceModel GetExperience(String studentId, int id) throws IOException;
	public ExperienceModel CreateExperience(String studentId, ExperienceSubmission model) throws IOException;
	public ExperienceModel UpdateExperience(String studentId, int id, ExperienceSubmission model) throws IOException;

}