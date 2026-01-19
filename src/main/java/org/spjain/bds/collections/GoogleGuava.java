package org.spjain.bds.collections;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class GoogleGuava {
    public static void main(String [] args) {
        Multiset<String> myset = TreeMultiset.create();
        System.out.println(myset.size());
        myset.add("Hello", 3);
        myset.add("Hello");
        myset.add("Dude");
        System.out.println(myset.size());
        System.out.println(myset.elementSet().size());
        System.out.println(myset.count("Hello"));
    }
}
