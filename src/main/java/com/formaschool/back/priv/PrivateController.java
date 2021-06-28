package com.formaschool.back.priv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.priv.dto.Private;

@RestController
@RequestMapping("private")
@CrossOrigin
public class PrivateController {

	@Autowired
	private PrivateService service;

	@GetMapping("ofUser/{userId}")
	private List<Private> findAllPrivateOfUser(@PathVariable String userId) {
		List<Private> x = service.findAllPrivateOfUser(userId);
		return x;
	}
}
