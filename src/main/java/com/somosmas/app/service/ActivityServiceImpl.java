package com.somosmas.app.service;

import com.somosmas.app.exception.custom.ActivityAlreadyExistException;
import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.response.ActivityResponse;
import com.somosmas.app.repository.IActivityRepository;
import com.somosmas.app.service.abstraction.IActivityService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements IActivityService {

    private static final String ACTIVITY_ID_NOT_FOUND = "Activity ID: {0} not found.";
    
	@Autowired
    private IActivityRepository activityRepository;

    @Override
    public void create(ActivityRequest activityRequest) throws ActivityAlreadyExistException {
        if (activityRepository.existsByName(activityRequest.getName())) {
            throw new ActivityAlreadyExistException(activityRequest.getName());
        }
        Activity activity = ConvertUtil.convertToEntity(activityRequest);
        activity.setSoftDelete(false);
        activity.setTimestamp(TimestampUtil.getCurrentTime());
        activityRepository.save(activity);
    }

	@Override
	public ActivityResponse update(ActivityRequest activityRequest, Long id) {
		Activity activity = activityRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format(ACTIVITY_ID_NOT_FOUND, id)));

		Activity activityUpdated = ConvertUtil.convertToEntity(activityRequest);
		activityUpdated.setIdActivity(id);
		activityUpdated.setTimestamp(TimestampUtil.getCurrentTime());
		activityUpdated.setSoftDelete(activity.isSoftDelete());
		activityRepository.save(activityUpdated);
		
		return ConvertUtil.convertToDto(activityUpdated);
	}

    

}
