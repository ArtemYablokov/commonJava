package com.yablokovs.generics;

import java.util.ArrayList;
import java.util.List;


/**
 * Безпасное извлечение без CAST - по верхней границе объявленной коллекции
 * Положить - все что входит в диапазон
 */
public class SuperVsExtends {

    public static void main(String[] args) {
        superNumber();
        extendNumber();
    }

    // P roducer
    // E xtends
    // C omsumer
    // S uper

    // TODO: 22.11.2022 EXTEND == PRODUCER
    private static void extendNumber() {
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        List<Double> doubles = new ArrayList<>();
        doubles.add(0.0);

        List<? extends Number> extendNumbers = integers;

//        extendNumbers.add(new Object());

        // TODO: 22.11.2022 нельзя потому чо может быть и INTEGER и DOUBLE коллекция присвоена - неизвесно что класть
//        extendNumbers.add(1);
//        extendNumbers.add(1L);
//        extendNumbers.add(1.0);
//        extendNumbers.add(0x1234569);

        // TODO: 22.11.2022 положить можно только NULL
        extendNumbers.add(null);

        // TODO: 22.11.2022 извлечь только NUMBER
        Number number = extendNumbers.get(0);

        Integer integer = (Integer) extendNumbers.get(0);
    }

    // TODO: 22.11.2022 SUPER == CONSUMER
    private static void superNumber() {
        List<? super Number> superNumbers = new ArrayList<>();

//        superNumbers.add("");

        // TODO: 22.11.2022 положить можно Number + наследников
        superNumbers.add(1);
        superNumbers.add(1L);
        superNumbers.add(1.0);
        superNumbers.add(0x1234569);
        superNumbers.add(null);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add("");

        superNumbers = objects;
        superNumbers.add(1);

        // TODO: 22.11.2022 вне зависимости от присвоенной коллекции извлечь можно только ВЕРХНЮЮ ГРАНИЦУ = Object
        Object object = superNumbers.get(0);
    }
}
