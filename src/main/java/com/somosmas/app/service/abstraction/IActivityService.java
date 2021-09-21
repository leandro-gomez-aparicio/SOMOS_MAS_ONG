package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.ActivityAlreadyExistException;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.response.ActivityResponse;

public interface IActivityService {
	
	void create(ActivityRequest activityRequest) throws ActivityAlreadyExistException;

	ActivityResponse update(ActivityRequest activity, Long id);
}
