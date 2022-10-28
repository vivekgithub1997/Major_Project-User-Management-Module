package com.vivek.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vivek.binding.CitizenApplication;
import com.vivek.model.CitizenApplicationModel;
import com.vivek.repo.CitizenApplicationEntityRepo;

@Service
public class CitizenApplicationServiceImpl implements CitizenApplicationService {

	@Autowired
	private CitizenApplicationEntityRepo repo;

	@Override
	public Integer createApplication(CitizenApplication application) {
		String url = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class, application.getSsn());

		String cityState = responseEntity.getBody();
		if ("New Jersey".equals(cityState)) {

			CitizenApplicationModel entity = new CitizenApplicationModel();
			entity.setStateName(cityState);
			BeanUtils.copyProperties(application, entity);
			repo.save(entity);
			return entity.getAppId();
		}

		return 0;
	}

}
