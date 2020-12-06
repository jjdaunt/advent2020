package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input06.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            lines.add("");
            int count = 0;
            int members = 0;
            int[] answers = new int[26];
            for (String line : lines) {
                if (line.length() == 0) {
                    for (int i = 0; i < 26; i++) {
                        if (answers[i] == members) {
                            count++;
                        }
                        answers[i] = 0;
                    }
                    members = 0;
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        answers[line.charAt(i) - 97]++;
                    }
                    members++;
                }
            }
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
