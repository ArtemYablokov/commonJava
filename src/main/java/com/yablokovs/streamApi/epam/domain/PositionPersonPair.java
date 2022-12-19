package com.yablokovs.streamApi.epam.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PositionPersonPair {

    private String position;
    private Person person;
}
