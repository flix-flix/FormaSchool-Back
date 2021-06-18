package com.formaschool.back.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.permission.PermissionMemberUserRoleWithoutRights;
import com.formaschool.back.dto.permission.PermissionRights;
import com.formaschool.back.dto.roles.DescriptionBoolean;
import com.formaschool.back.models.Permission;
import com.formaschool.back.repositories.PermissionRepository;
import com.formaschool.back.services.PermissionService;

public class PermissionServiceImpl extends CRUDServiceImpl<Permission> implements PermissionService {

	private PermissionRepository repository;

	public PermissionServiceImpl(PermissionRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	@Override
	public void deleteByRoleId(String roleId) {
		List<Permission> permissions = this.repository.findByRoleId(roleId);
		for (Permission permission : permissions) {
			this.repository.deleteById(permission.getId());
		}
	}

	@Override
	public List<PermissionMemberUserRoleWithoutRights> findPermissionBySalonId(String salonId) {
		List<Permission> permissions = this.repository.findBySalonId(salonId);
		return permissions.stream()
				.map(permission -> dto(permission,PermissionMemberUserRoleWithoutRights.class))
				.collect(Collectors.toList());
	}

	@Override
	public PermissionRights findPermissionRightsById(String permissionId) {
		Permission permission = this.repository.findById(permissionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		return new PermissionRights(permissionId, initCommonRights(permission));
	}
	
	/**
	 * Init a List with description and boolean
	 * 
	 * @param role a role object from database
	 * @return a list which contain description and boolean of commonRights
	 */
	public List<DescriptionBoolean> initCommonRights(Permission permission) {
		List<DescriptionBoolean> list = new ArrayList<DescriptionBoolean>();

		list.add(new DescriptionBoolean("Permet de gérer les permissions",
				permission.getCommonRights().getManagePermissions()));
		list.add(new DescriptionBoolean("Permet de changer le nom du salon", permission.getCommonRights().getUpdateSalon()));
		list.add(new DescriptionBoolean("Permet de supprimer les messages des autres",
				permission.getCommonRights().getDeleteMsg()));
		list.add(new DescriptionBoolean("Permet de tag les autres utilisateurs",
				permission.getCommonRights().getTagSomeone()));
		list.add(new DescriptionBoolean("Permet de voir le salon", permission.getCommonRights().getSeeSalon()));
		list.add(new DescriptionBoolean("Permet d'envoyer des messages", permission.getCommonRights().getSendMsg()));
		list.add(new DescriptionBoolean("Permet d'ajouter des reactions sous les messages",
				permission.getCommonRights().getAddReaction()));
		return list;
	}

	@Override
	public PermissionRights updatePermission(PermissionRights permissionRights) {
		Permission permission = this.get(permissionRights.getId());
		permission = getValueFromPermissionRights(permission, permissionRights);
		this.repository.save(permission);
		return permissionRights;
	}
	
	public Permission getValueFromPermissionRights(Permission permission, PermissionRights permissionRights) {
		permission.getCommonRights().setManagePermissions(permissionRights.getCommonRights().get(0).getValue());
		permission.getCommonRights().setUpdateSalon(permissionRights.getCommonRights().get(1).getValue());
		permission.getCommonRights().setDeleteMsg(permissionRights.getCommonRights().get(2).getValue());
		permission.getCommonRights().setTagSomeone(permissionRights.getCommonRights().get(3).getValue());
		permission.getCommonRights().setSeeSalon(permissionRights.getCommonRights().get(4).getValue());
		permission.getCommonRights().setSendMsg(permissionRights.getCommonRights().get(5).getValue());
		permission.getCommonRights().setAddReaction(permissionRights.getCommonRights().get(6).getValue());
		return permission;
	}
}
