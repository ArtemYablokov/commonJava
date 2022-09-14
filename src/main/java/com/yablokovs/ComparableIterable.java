package com.yablokovs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

public class ComparableIterable {

    public static void main(String[] args) {
        var objects = new ArrayList<MyClass>();
        objects.add(new MyClass(5));
        objects.add(new MyClass(1));
        objects.add(new MyClass(3));
        objects.add(new MyClass(0));

        Collections.sort(objects);


        System.out.println(objects);

        Stream<MyClass> sorted = objects.stream().sorted((o1, o2) -> Integer.compare(o1.value, o2.value));
//        sorted.collect() TRY to pass not Collector.toList
        Stream<MyClass> sorted1 = objects.stream().sorted(Comparator.comparingInt(o -> o.value));
    }


}


class ImplComparator implements Comparator<MyClass> {

    @Override
    public int compare(MyClass o1, MyClass o2) {
        return o1.compareTo(o2);
    }
}

@RequiredArgsConstructor
class MyClass implements Comparable<MyClass> {

    final int value;

    @Override
    public int compareTo(MyClass o) {
        return Integer.compare(value, o.value);
    }

    public int getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyClass)) return false;
        MyClass myClass = (MyClass) o;
        return getValue() == myClass.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}


class IterableClass implements Iterable<MyClass> {

    private List<MyClass> myClasses;

    @Override
    public Iterator<MyClass> iterator() {
        return new Iterator<MyClass>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public MyClass next() {
                return null;
            }
        };
    }
}
