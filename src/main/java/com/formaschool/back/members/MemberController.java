package com.formaschool.back.members;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.members.dto.MemberUserNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;

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
	
	@GetMapping("withoutPermission/{salonId}")
	public List<MemberUserNamePict> findMembersInTeamWithoutPermissionForSalon(@PathVariable String salonId){
		return this.service.findMembersInTeamWithoutPermissionForSalon(salonId);
	}
}