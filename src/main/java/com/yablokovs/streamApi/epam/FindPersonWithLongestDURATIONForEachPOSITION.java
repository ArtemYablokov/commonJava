package com.yablokovs.streamApi.epam;

import com.yablokovs.streamApi.epam.domain.Employee;
import com.yablokovs.streamApi.epam.domain.JobHistory;
import com.yablokovs.streamApi.epam.domain.Person;
import com.yablokovs.streamApi.epam.domain.PersonDurationPair;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindPersonWithLongestDURATIONForEachPOSITION {


    public static void main(String[] args) {
        toMapApproach();
    }

    public static void toMapApproach() {

        List<Employee> employees = GroupByPartitionByReduce.getEmployees();

        Map<String, Person> collect = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()

                        .collect(Collectors.toMap(JobHistory::getPosition,
                                jobHistory -> new PersonDurationPair(employee.getPerson(), jobHistory.getDuration()),
                                (pair1, pair2) -> new PersonDurationPair(pair1.getPerson(), pair1.getDuration() + pair2.getDuration())))
                        .entrySet()
                        .stream())

                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (entry1, entry2) -> entry1.getDuration() > entry1.getDuration() ? entry1 : entry2))
                .entrySet()
                .stream()

                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPerson()));
    }

    public static void groupingByApproach() {

        List<Employee> employees = GroupByPartitionByReduce.getEmployees();

        Map<String, Person> collect = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()
                        .collect(
                                Collectors.groupingBy(JobHistory::getPosition, Collectors.summingInt(JobHistory::getDuration)))
                        .entrySet()
                        .stream()

                        .map(entry -> new PersonPositionDuration(employee.getPerson(), entry.getKey(), entry.getValue())))
                .collect(
                        Collectors.groupingBy(PersonPositionDuration::getPosition,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingInt(PersonPositionDuration::getDuration)),
                                        optionalPersonPositionDuration -> optionalPersonPositionDuration
                                                .map(PersonPositionDuration::getPerson)
                                                .orElseThrow(IllegalStateException::new))));

    }
}

@Getter
class PersonPositionDuration {

    int duration;
    String position;
    Person person;


    public PersonPositionDuration(Person person, String position, int duration) {
        this.duration = duration;
        this.position = position;
        this.person = person;
    }
}