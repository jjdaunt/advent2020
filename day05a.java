package com.scratch;

import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input05.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            int max = 0;
            for (String line : lines) {
                int a = parseInt(line.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
                int b = parseInt(line.substring(7).replace('L', '0').replace('R', '1'), 2);
                max = Integer.max(max, a * 8 + b);
            }
            System.out.println(max);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
