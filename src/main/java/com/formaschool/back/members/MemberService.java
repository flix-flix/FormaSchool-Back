package com.formaschool.back.members;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.members.dto.MemberRoles;
import com.formaschool.back.members.dto.MemberUserNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.roles.dto.RoleWithoutRights;

public interface MemberService extends CRUDService<Member> {

	public List<Member> findAllByUserId(String userId);

	public List<Member> findByTeamId(String teamId);

	public List<MemberUserPseudo> findMemberUserPseudoByTeamId(String teamId);

	public List<MemberUserNamePict> findMembersInTeamWithoutPermissionForSalon(String salonId);

	// public MemberRoles addRoleToMember(MemberRoleUpdate dto, String roleId);

	public MemberRoles addRoleToMember(String memberId, String roleId);

	// ========

	public List<Member> findAllPrivateByUserId(String userId);

	public List<RoleWithoutRights> findRolesByMember(String idMember);
}
