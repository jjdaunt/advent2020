package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input09.txt"))) {
            List<Long> numbers = br.lines().map(Long::parseLong).collect(Collectors.toList());
            List<List<Long>> sums = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                List<Long> newList = new ArrayList<>();
                for (int j = 0; j < 25; j++) {
                    if (j >= i) {
                        continue;
                    }
                    newList.add(numbers.get(i) + numbers.get(j));
                }
                sums.add(newList);
            }
            for (int i = 25; i < numbers.size(); i++) {
                long num = numbers.get(i);
                if (sums.stream().noneMatch(l -> l.contains(num))) {
                    System.out.println(num);
                    return;
                } else {
                    List<Long> newList = new ArrayList<>();
                    sums.remove(0);
                    sums.forEach(l -> l.remove(0));
                    for (int j = 24; j > 0; j--) {
                        newList.add(num + numbers.get(i - j));
                    }
                    sums.add(newList);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
