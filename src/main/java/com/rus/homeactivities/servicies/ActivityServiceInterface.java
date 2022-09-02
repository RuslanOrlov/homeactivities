package com.rus.homeactivities.servicies;

import com.rus.homeactivities.models.Activity;

import java.util.List;

public interface ActivityServiceInterface {
    public Activity findById(Integer id);

    public List<Activity> findAll();

    public Activity saveOrUpdateActivity(Activity activity);

    public void deleteById(Integer id);
}
