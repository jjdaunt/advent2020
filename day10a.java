package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input10.txt"))) {
            List<Integer> lines = br.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
            lines.add(0, 0);
            int ones = 0;
            int threes = 1;
            for (int i = 1; i < lines.size(); i++) {
                int diff = lines.get(i) - lines.get(i - 1);
                if (diff == 1) {
                    ones++;
                } else if (diff == 3) {
                    threes++;
                }
            }
            System.out.println(ones * threes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
