package com.example.jira;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String siteName;
    private String projectType;
    private List<String> selectedOptions;

    public UserData() {
        selectedOptions = new ArrayList<>();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void addOption(String option) {
        if (!selectedOptions.contains(option)) {
            selectedOptions.add(option);
        }
    }



    public void limitToMax(int max) {
        while (selectedOptions.size() > max) {
            selectedOptions.remove(selectedOptions.size() - 1);
        }
    }
}
