package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input3.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            int count = 0;
            for (int i = 0; i < lines.size(); i++) {
                if (isTree(lines.get(i), i)) {
                    count++;
                }
            }
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isTree(String line, int i) {
        return line.charAt((i * 3) % line.length()) == '#';
    }
}
