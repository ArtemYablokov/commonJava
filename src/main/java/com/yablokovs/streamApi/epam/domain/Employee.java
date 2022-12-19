package com.yablokovs.streamApi.epam.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Employee {

    private Person person;

    private List<JobHistory> jobHistories;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    public void setJobHistories(List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }
}
