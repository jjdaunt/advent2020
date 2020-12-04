package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input04.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            lines.add("");
            int count = 0;
            Set<String> required = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
            Set<String> matched = new HashSet<>();
            for (String line : lines) {
                if (line.length() == 0) {
                    if (matched.size() == 7) {
                        count++;
                    }
                    matched.clear();
                } else {
                    String[] entries = line.split(" ");
                    for (String entry : entries) {
                        String code = entry.split(":")[0];
                        if (required.contains(code)) {
                            matched.add(code);
                        }
                    }
                }
            }
            System.out.println(count + " valid passports detected.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
