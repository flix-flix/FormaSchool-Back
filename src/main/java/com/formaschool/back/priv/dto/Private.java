package com.formaschool.back.priv.dto;

import java.util.List;

import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.salons.dto.SalonMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Private {
	private List<MemberUserPseudo> users;
	private SalonMessages salon;
}
