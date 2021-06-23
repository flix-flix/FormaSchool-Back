package com.formaschool.back.permissions.dto;

import java.util.List;

import com.formaschool.back.roles.dto.DescriptionBoolean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionRights {
	
	private String id;
	private List<DescriptionBoolean> commonRights;

}
