package com.vivek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.binding.CitizenApplication;
import com.vivek.service.CitizenApplicationService;

@RestController
public class CitizenApplicationController {

	@Autowired
	private CitizenApplicationService citizenService;

	@PostMapping("/app")
	public ResponseEntity<String> createApplication(@RequestBody CitizenApplication application) {

		Integer createApplication = citizenService.createApplication(application);
		if (createApplication > 0) {
			return new ResponseEntity<String>("APPLICATION ID :" + createApplication, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("INVALID SSN", HttpStatus.BAD_REQUEST);
		}
	}

}
