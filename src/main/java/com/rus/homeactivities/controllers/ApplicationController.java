package com.rus.homeactivities.controllers;

import com.rus.homeactivities.models.Activity;
import com.rus.homeactivities.models.Realization;
import com.rus.homeactivities.servicies.ActivityServiceInterface;
import com.rus.homeactivities.servicies.RealizationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ApplicationController {
    private final ActivityServiceInterface activityService;
    private final RealizationServiceInterface realizationService;

    private Integer currentActivityId;

    @Autowired
    public ApplicationController(ActivityServiceInterface activityService,
                              RealizationServiceInterface realizationService) {
        this.activityService = activityService;
        this.realizationService = realizationService;
    }

    // -------------------------------------------------------------------
    // Методы Контроллера по обработке списка записей плановых мероприятий
    // -------------------------------------------------------------------
    @GetMapping("/start")
    public String startMethod() {
        return "start";
    }

    @GetMapping("/activities")
    public String showAllActivities(Model model) {
        List<Activity> activities = activityService.findAll();
        model.addAttribute("activities", activities);
        return "activities-list";
    }

    @GetMapping("/activities/{id}")
    public String showActivityById(@PathVariable("id") Integer id, Model model) {
        currentActivityId = activityService.findById(id).getId();
        model.addAttribute("activity", activityService.findById(id));
        return "activity-card";
    }

    @GetMapping("/activities/new")
    public String openCreateActivityForm(Activity activity) {
        return "activity-create";
    }

    @PostMapping("/activities")
    public String createActivity(@Valid Activity activity, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "activity-create";

        activityService.saveOrUpdateActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/activities/{id}/edit")
    public String openUpdateActivityForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("activity", activityService.findById(id));
        return "activity-edit";
    }

    @PatchMapping("/activities")
    public String updateActivity(@Valid Activity activity, BindingResult bindingResult) {
        activity.setChangeDate(LocalDateTime.now());
        if (bindingResult.hasErrors())
            return "activity-edit";

        activityService.saveOrUpdateActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/activities/{id}/remove")
    public String deleteActivity(@PathVariable("id") Integer id) {
        activityService.deleteById(id);
        return "redirect:/activities";
    }

    // -----------------------------------------------------------------------------------
    // Методы Контроллера по обработке списка записей хода реализации текущего мероприятия
    // -----------------------------------------------------------------------------------
    @GetMapping("/realizations")
    public String showAllRealizationsOfCurrentActivity(Model model) {
        if (currentActivityId == null)
            return "redirect:/activities";

        model.addAttribute("activity",
                activityService.findById(currentActivityId));
        model.addAttribute("realizationList",
                activityService.findById(currentActivityId).getRealizationList());
        return "realizations-list";
    }

    @GetMapping("/realizations/{id}")
    public String showRealizationById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("realization", realizationService.findById(id));
        return "realization-card";
    }

    @GetMapping("/realizations/new")
    public String openCreateRealizationForm(Model model) {
        if (currentActivityId == null)
            return "redirect:/activities";

        Realization realization = new Realization();
        realization.setActivity(activityService.findById(currentActivityId));

        model.addAttribute("realization", realization);
        return "realization-create";
    }

    @PostMapping("/realizations")
    public String createRealization(@Valid Realization realization, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "realization-create";

        realizationService.saveOrUpdateRealization(realization);
        return "redirect:/realizations";
    }

    @GetMapping("/realizations/{id}/edit")
    public String openUpdateRealizationForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("realization", realizationService.findById(id));
        return "realization-edit";
    }

    @PatchMapping("/realizations")
    public String updateRealization(@Valid Realization realization, BindingResult bindingResult) {
        realization.setChangeDate(LocalDateTime.now());
        if (bindingResult.hasErrors())
            return "realization-edit";

        realizationService.saveOrUpdateRealization(realization);
        return "redirect:/realizations";
    }

    @GetMapping("/realizations/{id}/remove")
    public String deleteRealization(@PathVariable("id") Integer id) {
        realizationService.deleteById(id);
        return "redirect:/realizations";
    }
}
