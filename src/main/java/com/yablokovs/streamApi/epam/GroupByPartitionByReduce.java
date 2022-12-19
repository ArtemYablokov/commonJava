package com.yablokovs.streamApi.epam;

import com.yablokovs.streamApi.epam.domain.Employee;
import com.yablokovs.streamApi.epam.domain.JobHistory;
import com.yablokovs.streamApi.epam.domain.Person;
import com.yablokovs.streamApi.epam.domain.PositionPersonPair;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByPartitionByReduce {


    public static List<Employee> getEmployees() {

//        List<Employee> employees = new ArrayList<>();

        Person Ivan = new Person("Ivan", 30);
        Person Eugen = new Person("Eugen", 20);
        Person Vlad = new Person("Vlad", 40);
        Person Elena = new Person("Elena", 35);

        JobHistory dev2 = new JobHistory("dev", 2);
        JobHistory dev1 = new JobHistory("dev", 1);
        JobHistory test1 = new JobHistory("test", 1);
        JobHistory qa2 = new JobHistory("QA", 2);

        Employee employee1 = new Employee(Ivan, List.of(dev2));
        Employee employee2 = new Employee(Eugen, List.of(dev1));
        Employee employee3 = new Employee(Vlad, List.of(test1));
        Employee employee4 = new Employee(Elena, List.of(qa2));

        return List.of(employee1, employee2, employee3, employee4);
    }

    public static void groupByJobTitle() {
        List<Employee> employees = getEmployees();

        // TODO: 15.12.2022 REDUCE SOLUTION

        HashMap<String, Set<Person>> reduce = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()
                        .map(jobHistory -> new PositionPersonPair(jobHistory.getPosition(), employee.getPerson())))

                .reduce(new HashMap<>(), GroupByPartitionByReduce::addToMap, GroupByPartitionByReduce::merge2Maps); // TODO: 16.12.2022 MERGE - for parallel operations!

        // TODO: 16.12.2022 COLLECTOR = SAME AS REDUCE but can modify ACCUMULATOR in addToMap and merge2Maps !!!

        // TODO: 15.12.2022 GROUPING

        Map<String, Set<Person>> grouping = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()
                        .map(jobHistory -> new PositionPersonPair(jobHistory.getPosition(), employee.getPerson())))

                .collect(Collectors.groupingBy(PositionPersonPair::getPosition,
                        Collectors.mapping(PositionPersonPair::getPerson, Collectors.toSet())));

    }

    static HashMap<String, Set<Person>> addToMap(HashMap<String, Set<Person>> map, PositionPersonPair positionPersonPair) {

        // TODO: 12.12.2022 REDUCE не может модифицировать входные параметры -> надо создавать новую мапу
        HashMap<String, Set<Person>> copyOf = new HashMap<>(map);

        copyOf.compute(positionPersonPair.getPosition(), ((s, persons) -> {
            persons = persons == null ? new HashSet<>() : persons;
            persons.add(positionPersonPair.getPerson());
            return persons;
        }));

        return copyOf;
    }

    static HashMap<String, Set<Person>> merge2Maps(HashMap<String, Set<Person>> map1, HashMap<String, Set<Person>> map2) {
        HashMap<String, Set<Person>> newMap = new HashMap<>(map1);


//        newMap.putAll(map2); // не подходит тк не знает как сливать два SET по одному KEY
        map2.forEach(((key, value) -> {
            newMap.compute(key, ((string, persons) -> {
                persons = persons == null ? new HashSet<>() : persons;
                persons.addAll(value);
                return persons;
            }));
        }));

        // OR
        map2.forEach(((key, value) -> newMap.merge(key, value, (people, people2) -> {
            people.addAll(people2);
            return people;
        })));


        return newMap;
    }

    public static void main(String[] args) {
        groupByJobTitle();
    }

}

