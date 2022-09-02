package com.rus.homeactivities.servicies;

import com.rus.homeactivities.models.Realization;

import java.util.List;

public interface RealizationServiceInterface {
    public Realization findById(Integer id);

    public List<Realization> findAll();

    public Realization saveOrUpdateRealization(Realization realization);

    public void deleteById(Integer id);
}
