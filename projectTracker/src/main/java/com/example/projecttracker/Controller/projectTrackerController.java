package com.example.projecttracker.Controller;

import com.example.projecttracker.ApiRespose.ApiResponse;
import com.example.projecttracker.Model.ProjectTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/project_tracker")
public class projectTrackerController {
    ArrayList<ProjectTracker> projectTrackers = new ArrayList<>();

    @GetMapping("/display")
    public ArrayList<ProjectTracker> getProjectTrackers() {
        return projectTrackers;
    }

    @GetMapping("/search/{key}")
    public ProjectTracker getProjectSearch(@PathVariable String key) {
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getTitale().equals(key)) {
                return projectTracker;
            }

        }
        return null;
    }

    @GetMapping("/company_projects/{company_name}")
    public ApiResponse getComProjectTrackers(@PathVariable String company_name) {
        ArrayList<ProjectTracker> projectTrackersComp = new ArrayList<>();
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getCompanyName().equals(company_name)) {
            }
            projectTrackersComp.add(projectTracker);
        }
        return new ApiResponse("the cpmany (" + company_name + ") has thees projects " + projectTrackersComp.toString());
    }

    @PostMapping("/post")
    public ApiResponse addProjectTracker(@RequestBody ProjectTracker projectTracker) {
        projectTrackers.add(projectTracker);
        return new ApiResponse("Successfully added project ");
    }

    @PutMapping("/update/{key}")
    public ApiResponse updateProjectTracker(@RequestBody ProjectTracker projectTracker, @PathVariable int key) {
        projectTrackers.set(key, projectTracker);
        return new ApiResponse("Successfully updated project ");
    }

    @PutMapping("/updateStatus/{key}")
    public ApiResponse updateProjectTrackerStatus(@RequestBody ProjectTracker projectTracker, @PathVariable int key) {
        if (projectTrackers.get(key).getStatus().equals("done")) {
            projectTrackers.get(key).setStatus("not done");
        } else projectTrackers.get(key).setStatus(" done");

        return new ApiResponse("Successfully updated project status");
    }

    @DeleteMapping("/delete/{key}")
    public ApiResponse deleteProjectTracker(@PathVariable int key) {
        projectTrackers.remove(projectTrackers.get(key));
        return new ApiResponse("Successfully deleted project ");
    }

}
