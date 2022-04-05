package com.ostrovsky;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underageCount = persons.stream()
                .filter(i -> i.getAge() < 18)
                .count();

//        System.out.println(underageCount);

        List<String> conscripts = persons.stream()
                .filter(i -> i.getAge() >= 18 && i.getAge() < 27)
                .filter(i -> i.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

//        System.out.println(conscripts);

        List<Person> workers = persons.stream()
                .filter(i -> i.getAge() >= 18 && ((i.getAge() < 65 && i.getSex() == Sex.MAN) || (i.getAge() < 60 && i.getSex() == Sex.WOMAN)))
                .filter(i -> i.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

//        System.out.println(workers);
    }
}
