package com.yablokovs.immutableunmodifiable;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutableArrays {

    public static void main(String[] args) {

        // TODO: 10.09.2022
        // System.arraycopy
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2));

        boolean add = list.add(3); // YES
        add = list.add(3); // YES
        list.set(0, 0); // YES
        Integer remove = list.remove(2); // YES


        //        Returns a fixed-size list backed by the specified array
        // TODO: 30.03.2022 FIXED SIZE
        List<Integer> integers2 = Arrays.asList(1, 2);
        // integers2.add(0); // NO
        Integer set = integers2.set(0, 0); // YES
        // Integer remove = integers2.remove(0); // NO



        // Returns an unmodifiable list
        // TODO: 30.03.2022 COMPLETELY IMMUTABLE
        List<Integer> integers1 = List.of(1, 2);
        // integers1.remove(0); // NO
        // Integer set1 = integers1.set(0, 0); // NO
        // integers1.add(0); // NO


        // DIFF List.of ???
        ImmutableList<Integer> of = ImmutableList.of(1, 2, 3);
    }
}
