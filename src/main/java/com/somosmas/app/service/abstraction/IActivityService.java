package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.ActivityAlreadyExistException;
import com.somosmas.app.model.request.ActivityRequest;

public interface IActivityService {
	
	void create(ActivityRequest activityRequest) throws ActivityAlreadyExistException;

}
