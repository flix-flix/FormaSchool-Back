package com.formaschool.back.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.roles.DescriptionBoolean;
import com.formaschool.back.dto.roles.RoleCreate;
import com.formaschool.back.dto.roles.RoleWithDescription;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Member;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Salon;
import com.formaschool.back.models.TeamSalonRights;
import com.formaschool.back.repositories.RoleRepository;
import com.formaschool.back.services.PermissionService;
import com.formaschool.back.services.RoleService;
import com.formaschool.back.services.SalonService;
import com.formaschool.back.services.TeamService;

public class RoleServiceImpl extends CRUDServiceImpl<Role> implements RoleService {

	private RoleRepository repo;
	private TeamService teamService;
	private SalonService salonService;
	private PermissionService permissionService;

	public RoleServiceImpl(RoleRepository repo, SalonService salonService, PermissionService permissionService,
			TeamService teamService, ObjectMapper mapper) {
		super(repo, mapper);
		this.teamService = teamService;
		this.salonService = salonService;
		this.permissionService = permissionService;
		this.repo = repo;
	}

	public List<RoleWithoutRights> findAllWithoutRights() {
		return repo.findAll().stream().map(role -> dto(role, RoleWithoutRights.class)).collect(Collectors.toList());
	}

	@Override
	public RoleWithDescription findRoleWithDescriptionById(String id) {
		Role role = this.repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		List<DescriptionBoolean> rights = initRights(role);
		List<DescriptionBoolean> commonRights = initCommonRights(role);
		return new RoleWithDescription(role.getId(), role.getName(), role.getColor(), rights, commonRights);
	}

	/**
	 * Init a List with description and boolean
	 * 
	 * @param role a role object from database
	 * @return a list which contain description and boolean of rights
	 */
	public List<DescriptionBoolean> initRights(Role role) {
		List<DescriptionBoolean> list = new ArrayList<DescriptionBoolean>();
		list.add(new DescriptionBoolean("Permet de gérer une equipe, changer le nom et la description",
				role.getManageTeam()));
		list.add(new DescriptionBoolean("Permet de créer et de supprimer un salon", role.getCreateDeleteSalon()));
		list.add(new DescriptionBoolean("Permet de créer, update et supprimer un emoji", role.getManageEmoji()));
		list.add(new DescriptionBoolean("Permet de voir les logs", role.getSeeLogs()));
		list.add(new DescriptionBoolean("Permet de changer son pseudo", role.getChangePseudo()));
		list.add(new DescriptionBoolean("Permet de changer tous les pseudos", role.getManagePseudo()));
		return list;
	}

	/**
	 * Init a List with description and boolean
	 * 
	 * @param role a role object from database
	 * @return a list which contain description and boolean of commonRights
	 */
	public List<DescriptionBoolean> initCommonRights(Role role) {
		List<DescriptionBoolean> list = new ArrayList<DescriptionBoolean>();

		list.add(new DescriptionBoolean("Permet de gérer les permissions",
				role.getCommonRights().getManagePermissions()));
		list.add(new DescriptionBoolean("Permet de changer le nom du salon", role.getCommonRights().getUpdateSalon()));
		list.add(new DescriptionBoolean("Permet de supprimer les messages des autres",
				role.getCommonRights().getDeleteMsg()));
		list.add(new DescriptionBoolean("Permet de tag les autres utilisateurs",
				role.getCommonRights().getTagSomeone()));
		list.add(new DescriptionBoolean("Permet de voir le salon", role.getCommonRights().getSeeSalon()));
		list.add(new DescriptionBoolean("Permet d'envoyer des messages", role.getCommonRights().getSendMsg()));
		list.add(new DescriptionBoolean("Permet d'ajouter des reactions sous les messages",
				role.getCommonRights().getAddReaction()));
		return list;
	}

	@Override
	public Role updateFromRoleWithDesc(RoleWithDescription roleDesc) {
		Role role = this.repo.findById(roleDesc.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		role = updateCommonRights(role, roleDesc);
		role = updateRights(role, roleDesc);
		role.setColor(roleDesc.getColor());
		role.setName(roleDesc.getName());
		return this.repo.save(role);
	}

	public Role updateCommonRights(Role role, RoleWithDescription roleDesc) {
		role.getCommonRights().setManagePermissions(roleDesc.getCommonRights().get(0).getValue());
		role.getCommonRights().setUpdateSalon(roleDesc.getCommonRights().get(1).getValue());
		role.getCommonRights().setDeleteMsg(roleDesc.getCommonRights().get(2).getValue());
		role.getCommonRights().setTagSomeone(roleDesc.getCommonRights().get(3).getValue());
		role.getCommonRights().setSeeSalon(roleDesc.getCommonRights().get(4).getValue());
		role.getCommonRights().setSendMsg(roleDesc.getCommonRights().get(5).getValue());
		role.getCommonRights().setAddReaction(roleDesc.getCommonRights().get(6).getValue());
		return role;
	}

	public Role updateRights(Role role, RoleWithDescription roleDesc) {
		role.setManageTeam(roleDesc.getRights().get(0).getValue());
		role.setCreateDeleteSalon(roleDesc.getRights().get(1).getValue());
		role.setManageEmoji(roleDesc.getRights().get(2).getValue());
		role.setSeeLogs(roleDesc.getRights().get(3).getValue());
		role.setChangePseudo(roleDesc.getRights().get(4).getValue());
		role.setManagePseudo(roleDesc.getRights().get(5).getValue());
		return role;
	}

	@Override
	public Role addNewRole(String teamId, RoleCreate newRole) {
		TeamSalonRights defaultRights = new TeamSalonRights(true, true, true, true, true, true, true);

		Role role = this.save(
				new Role(newRole.getName(), newRole.getColor(), defaultRights, true, true, true, true, true, true));
		this.teamService.addRoleToTeam(teamId, role);
		return role;
	}

	@Override
	public void deleteRole(String teamId, String roleId) {
		Role role = this.get(roleId);

		List<Salon> salons = this.salonService.findAllSalonOfTeam(teamId);
		// delete his permission of all salon
		for (Salon salon : salons) {
			this.permissionService.deleteByRoleId(role.getId());
		}
		// delete the role from the team
		this.teamService.deleteRole(teamId, roleId);
		// finally, delete the role
		this.delete(role.getId());
	}

	@Override
	public List<RoleWithoutRights> findAllWithoutRightsByTeamId(String teamId) {
		return this.teamService.findRoleWithoutRightsByTeamId(teamId);
	}

	@Override
	public List<Role> findRoleMissingByMember(Member member) {
		List<RoleWithoutRights> roles= this.teamService.findRoleWithoutRightsByTeamId(member.getTeam().getId()); 
		
		/*for (RoleWithoutRights role : roles) {
			member.getRoles().stream().filter(memberRole -> memberRole.getId()==role.getId());
		}*/
			
		}
	}

	

