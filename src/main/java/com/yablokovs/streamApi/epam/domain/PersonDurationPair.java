package com.yablokovs.streamApi.epam.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PersonDurationPair {

    Person person;
    int duration;

}
