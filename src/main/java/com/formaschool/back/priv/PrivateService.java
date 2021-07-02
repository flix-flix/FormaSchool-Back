package com.formaschool.back.priv;

import java.util.List;

import com.formaschool.back.priv.dto.Private;

public interface PrivateService {
	public List<Private> findAllPrivateOfUser(String userId);
}
