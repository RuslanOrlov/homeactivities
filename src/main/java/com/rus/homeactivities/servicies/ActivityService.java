package com.rus.homeactivities.servicies;

import com.rus.homeactivities.models.Activity;
import com.rus.homeactivities.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements ActivityServiceInterface {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity findById(Integer id) {
        return activityRepository.getReferenceById(id);
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll(Sort.by("id"));
    }

    @Override
    public Activity saveOrUpdateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteById(Integer id) {
        activityRepository.deleteById(id);
    }
}
