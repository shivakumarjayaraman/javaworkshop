package org.spjain.bds.collections;

import java.util.*;

public class PlayWithCollections {
    public static void main(String[] args) {
        System.out.println("Hello, Collections!");

        // The List interface
        // Example of list
        List<String> myList = new ArrayList<>();
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Cherry");
        myList.add("Plum");
        myList.add("Orange");
        myList.add("Pineapple");
        System.out.println("My List: " + myList);
        System.out.println(myList.size());
        System.out.println("Element at index 1: " + myList.get(1));
        System.out.println("Iterating over list:");
        for (String item : myList) {
            System.out.println(item);
        }
        myList.remove("Banana");
        System.out.println("After removing Banana: " + myList);


        // The Queue interface : First-In-First-Out (FIFO) data structure

        // Also note the use of wrapper types and boxing/unboxing in effect here
        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(10);
        myQueue.add(20);
        System.out.println("Queue : " + myQueue);
        int val = myQueue.remove();
        System.out.println("Removed from queue: " + val);
        myQueue.add(30);
        System.out.println(myQueue);

        // The Stack interface : Last-In-First-Out (LIFO) data structure
        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        System.out.println("Stack : " + myStack);
        val = myStack.pop();
        System.out.println("Popped from stack: " + val);
        System.out.println(myStack);

        // The Set interface
        Set<String> mySet = new HashSet<>();
        mySet.add("Apple");
        mySet.add("Banana");
        mySet.add("Carrot");
        mySet.add("Apple"); // Duplicate, will not be added
        System.out.println("Set : " + mySet);

        // Use of equals and hashCode methods in Set
        // Example in org.spjain.bds.oop.TheObject where
        // we defined equals and hashCode in Person class

        // The SortedSet interface
        SortedSet<String> mySortedSet = new TreeSet<>();
        mySortedSet.add("Apple");
        mySortedSet.add("Carrot");
        mySortedSet.add("Apple"); // Duplicate, will not be added
        mySortedSet.add("Banana");
        System.out.println("Sorted Set : " + mySortedSet);

        // The Map interface
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Apple", 1);
        myMap.put("Banana", 2);
        myMap.put("Carrot", 3);
        System.out.println("Map : " + myMap);
        // Iterate over map
        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // get keys from map
        Set<String> keys = myMap.keySet();
        System.out.println("Keys in map: " + keys);
        for (String key : keys) {
            System.out.println("Key: " + key + ", Value: " + myMap.get(key));
        }

        // get values from map
        Collection<Integer> values = myMap.values();
        System.out.println("Values in map: " + values);

        // Unmodifiable Collections
        List<String> unmodifiableList = Collections.unmodifiableList(myList);
        // unmodifiableList.add("Durian"); // This will throw UnsupportedOperationException

        // sorting collections
        System.out.println("Iniial list" + myList);
        myList.sort((a, b) -> a.length() - b.length()); // ascending
        System.out.println("After sorting " + myList);

        // Iterators : Pattern to traverse collections
        Iterator<String> iterator = myList.iterator();
        System.out.println("Iterating using Iterator:");
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

        // Collections and type safety
        List<Object> objects = new ArrayList<>();
        objects.add("String");
        objects.add(10); // Integer
        System.out.println("Objects list: " + objects);
    }
}
