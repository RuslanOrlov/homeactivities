package com.rus.homeactivities.servicies;

import com.rus.homeactivities.models.Realization;
import com.rus.homeactivities.repositories.RealizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RealizationService implements RealizationServiceInterface {
    private final RealizationRepository realizationRepository;

    @Autowired
    public RealizationService(RealizationRepository realizationRepository) {
        this.realizationRepository = realizationRepository;
    }

    @Override
    public Realization findById(Integer id) {
        return realizationRepository.getReferenceById(id);
    }

    @Override
    public List<Realization> findAll() {
        return realizationRepository.findAll(Sort.by("id"));
    }

    @Override
    public Realization saveOrUpdateRealization(Realization realization) {
        return realizationRepository.save(realization);
    }

    @Override
    public void deleteById(Integer id) {
        realizationRepository.deleteById(id);
    }
}
