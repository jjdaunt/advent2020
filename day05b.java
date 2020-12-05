package com.scratch;

import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input05.txt"))) {
            List<Integer> values = br.lines().map(Main::convert).sorted().collect(Collectors.toList());
            for (int i = 0; i < values.size() - 1; i++) {
                if (values.get(i + 1) - values.get(i) == 2) {
                    System.out.println(values.get(i) + 1);
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int convert(String line) {
        int a = parseInt(line.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
        int b = parseInt(line.substring(7).replace('L', '0').replace('R', '1'), 2);
        return a * 8 + b;
    }
}
