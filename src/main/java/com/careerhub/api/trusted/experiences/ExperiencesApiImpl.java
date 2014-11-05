package com.careerhub.api.trusted.experiences;

import java.io.IOException;
import com.careerhub.api.RestHttpClient;

public class ExperiencesApiImpl implements AutoCloseable, ExperiencesApi {
    private String ApiBase = "api/trusted/v1/experiences";
    
	private RestHttpClient client;
	
	public ExperiencesApiImpl(String baseUrl, String accessToken) {
		client = new RestHttpClient(baseUrl, ApiBase);
		client.addOAuthHeader(accessToken);
	}
	
    @Override
	public ExperienceModel[] GetExperiences(String studentId) throws IOException {
        String resource = getResourceUrl(studentId);
        return client.getResource(resource, ExperienceModel[].class);
	}

    @Override
	public ExperienceModel GetExperience(String studentId, int id) throws IOException {
        String resource = getResourceUrl(studentId, id);
        return client.getResource(resource, ExperienceModel.class);
    }

    @Override
	public ExperienceModel CreateExperience(String studentId, ExperienceSubmission model) throws IOException {
        String resource = getResourceUrl(studentId);
        return client.postResource(resource, model, ExperienceModel.class);
    }


    @Override
	public ExperienceModel UpdateExperience(String studentId, int id, ExperienceSubmission model) throws IOException {
        String resource = getResourceUrl(studentId, id);
        return client.putResource(resource, model, ExperienceModel.class);
    }	

	@Override
	public void close() throws Exception {
		client.close();
	}

    private String getResourceUrl(String studentId) {
    	return getResourceUrl(studentId, null);
    }
    
    private String getResourceUrl(String studentId, Integer id) {
        String resouce = studentId;

        if (id != null) {
            resouce += "/" + id;
        }

        return resouce;
    }
}
