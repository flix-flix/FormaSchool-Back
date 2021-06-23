package com.formaschool.back.members;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.members.dto.MemberUserNamePict;

public interface MemberService extends CRUDService<Member> {

	public List<Member> findAllByUserId(String userId);

	public List<Member> findMembersByTeamId(String teamId);

	public List<MemberUserNamePict> findMembersInTeamWithoutPermissionForSalon(String salonId);
}
