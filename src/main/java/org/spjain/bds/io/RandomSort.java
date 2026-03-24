package org.spjain.bds.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RandomSort {

    private static int genRandomNumber(int max) {
        return (int) (Math.random() * max);
    }

    public static void main(String[] args) throws Exception {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/tmp/random_numbers.txt"))) {
            for (int i = 0; i < 100; i++) {
                bw.write(genRandomNumber(10000) + "\n");
            }
        }
        List<String> lines = Files.readAllLines(Path.of("/tmp/random_numbers.txt"));
        List<Integer> list = lines.stream().map(i -> Integer.parseInt(i)).sorted().toList();
        System.out.println(list);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/tmp/sorted_random_numbers.txt"))) {
            for (int number : list) {
                bw.write(number + "\n");
            }
        }


    }
}
