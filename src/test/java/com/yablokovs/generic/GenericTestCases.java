package com.yablokovs.generic;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GenericTestCases {

    @Test
    public void generics() {

        // Clazz implements IFace
        List<IFace> interfaces = new ArrayList<>(); // collection of IFACES

        interfaces.add(new Clazz()); // add CLASS to IFACE

        getCollectionOfInterfaces(interfaces); // PASS TO IFACE- expected method

        Map<String, Collection<Clazz>> faces = new HashMap<>(); // collection of CLASSES
        faces.put("", List.of(new Clazz()));

        getCollectionOfTextends(faces); // PASS TO <T extends IFace> - expected method


        // НЕЛЬЗЯ потому что коллекции не наследуются (в отличии от массивов)
//        Map<String, Collection<IFace>> фвфсы = faces;
//        List<Clazz> classes = new ArrayList<>();
//        interfaces = classes;


    }
    private void getCollectionOfInterfaces(Collection<IFace> IFaces) {}
    private <T extends IFace> void getCollectionOfTextends(Map<String, Collection<T>> faces) {}

}
