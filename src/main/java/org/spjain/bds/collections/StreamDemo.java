package org.spjain.bds.collections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String [] args) {
        record Person(String name, int age) {}

      /*  record Person(String name, int age) implements Comparable{
            @Override
            public int compareTo(Object o) {
                if (o instanceof Person p) {
                    return this.name.compareTo(p.name);
                }
                throw new IllegalArgumentException("Cannot compare with non-Person object");
            }
        }*/

        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob", 25);
        Person p3 = new Person("Charlie", 35);
        Person p4 = new Person("David", 28);

        List<String> list = List.of(p1, p2, p3, p4)
                .stream()
                .filter(p -> p.age() > 29)
                .map(Person::name)
                .toList();
        System.out.println(list);

        System.out.println(List.of(p1, p3, p2, p4)
                .stream()
                .sorted(Comparator.comparing(Person::name))
                //.sorted()
                //.map(Person::name)
                .toList());

        // flatmapping
        // a university which has a name and courses
        record University(String name, List<String> courses) {}
        University spJain = new University(
                "SPJain", List.of("Business", "Data Science", "Computer Science"));
        University univSyd = new University(
                "University of Sydney", List.of("Business", "Data Science", "Computer Science"));

        // we want to get a list of all courses offered by these universities
        List<String> allCourses = Stream.of(spJain, univSyd) // returns a stream of universities
                .flatMap(u -> u.courses().stream()) // returns a stream of courses names and flattens it
                .parallel()
                .distinct() // removes duplicates from the stream of courses
                .toList();
        System.out.println(allCourses);

        // reduce operations
        List.of(1, 2, 3, 4, 5)
                .stream()
                //.parallel()
                .max(Comparator.naturalOrder())
                .ifPresent(max -> System.out.println("Max : " + max));

        // reduce/fold
        List.of("hello", "happy", "world", "goodbye", "evil", "world")
                .stream()
                .reduce((s1, s2) -> s1+s2)
                .ifPresent(concated -> System.out.println("Concatenated string: " + concated));


    }
}
