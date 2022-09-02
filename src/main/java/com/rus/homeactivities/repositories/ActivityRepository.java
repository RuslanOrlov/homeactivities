package com.rus.homeactivities.repositories;

import com.rus.homeactivities.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
