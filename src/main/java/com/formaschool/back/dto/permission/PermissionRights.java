package com.formaschool.back.dto.permission;

import java.util.List;

import com.formaschool.back.dto.roles.DescriptionBoolean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionRights {
	
	private String id;
	private List<DescriptionBoolean> commonRights;

}
