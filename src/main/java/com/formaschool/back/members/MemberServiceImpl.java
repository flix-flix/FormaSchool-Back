package com.formaschool.back.members;

import java.util.List;
import java.util.stream.Collectors;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.members.dto.MemberRoles;
import com.formaschool.back.members.dto.MemberUserNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.permissions.PermissionService;
import com.formaschool.back.roles.Role;
import com.formaschool.back.roles.RoleService;
import com.formaschool.back.roles.dto.RoleWithoutRights;
import com.formaschool.back.salons.Salon;
import com.formaschool.back.salons.SalonService;

public class MemberServiceImpl extends CRUDServiceImpl<Member> implements MemberService {

	private MemberRepository repo;

	private PermissionService permissionService;
	private SalonService salonService;
	private RoleService roleService;

	public MemberServiceImpl(MemberRepository repo, Utils utils, PermissionService permissionService,
			SalonService salonService, RoleService roleService) {
		super(repo, utils);
		this.repo = repo;
		this.permissionService = permissionService;
		this.salonService = salonService;
		this.roleService = roleService;
	}

	// ====================================================================================================

	@Override
	public List<Member> findAllByUserId(String userId) {
		return repo.findByUserId(userId);
	}

	// ====================================================================================================

	@Override
	public List<Member> findByTeamId(String teamId) {
		return this.repo.findMemberByTeamId(teamId);
	}

	@Override
	public List<MemberUserPseudo> findMemberUserPseudoByTeamId(String teamId) {
		return findByTeamId(teamId).stream().map(member -> dto(member, MemberUserPseudo.class))
				.collect(Collectors.toList());
	}

	// ====================================================================================================
	@Override
	public List<MemberUserNamePict> findMembersInTeamWithoutPermissionForSalon(String salonId) {
		Salon salon = this.salonService.get(salonId);
		List<Member> members = this.repo.findMemberByTeamId(salon.getTeam().getId());
		return members.stream()
				.filter(member -> this.permissionService.findBySalonIdAndMemberId(salonId, member.getId()) == null)
				.map(member -> dto(member, MemberUserNamePict.class)).collect(Collectors.toList());
	}

	@Override
	public List<Member> findAllPrivateByUserId(String userId) {
		return repo.findByUserIdAndPrivTrue(userId);
	}

	@Override
	public MemberRoles addRoleToMember(String memberId, String roleId) {
		Member entity = opt(repo.findById(memberId));
		List<Role> roles = entity.getRoles();
		roles.add(new Role(roleId));
		entity.setRoles(roles);
		Member result = this.repo.save(entity);
		return dto(result, MemberRoles.class);
	}

	@Override
	public void deleteRoleToMember(String memberId, String roleId) {
		Member entity = opt(repo.findById(memberId));
		System.out.println(repo.save(entity));
		entity.setRoles(
				entity.getRoles().stream().filter(role -> !role.getId().equals(roleId)).collect(Collectors.toList()));
		this.repo.save(entity);
		System.out.println(repo.save(entity));
	}

	@Override
	public List<RoleWithoutRights> findRolesByMember(String idMember) {
		Member entity = opt(repo.findById(idMember));
		List<Role> roles = entity.getRoles();
		return roles.stream().map(role -> dto(role, RoleWithoutRights.class)).collect(Collectors.toList());
	}

}
