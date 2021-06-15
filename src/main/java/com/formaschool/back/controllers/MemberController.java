package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.Member;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.MemberService;

@RestController
@RequestMapping("members")
@CrossOrigin
public class MemberController implements CRUDController<Member> {

	@Autowired
	private MemberService service;

	@Override
	public CRUDService<Member> getGenericService() {
		return service;
	}
	
	@GetMapping ("findByTeamId/{teamId}")
	public List<Member> findMembersByTeamId (@PathVariable String teamId) {
		return this.service.findMembersByTeamId(teamId);
		
	}
}
