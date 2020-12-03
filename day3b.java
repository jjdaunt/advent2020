package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input3.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            int[] xArr = {1, 3, 5, 7, 1};
            int[] yArr = {1, 1, 1, 1, 2};
            long product = 1;
            for (int j = 0; j < 5; j++) {
                int count = 0;
                for (int i = 0; i < lines.size(); i+= yArr[j]) {
                    if (isTree(lines.get(i), i, xArr[j], yArr[j])) {
                        count++;
                    }
                }
                System.out.println(count);
                product *= count;
            }
            System.out.println(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isTree(String line, int i, int x, int y) {
        return line.charAt((i / y * x) % line.length()) == '#';
    }
}
