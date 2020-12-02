package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input1.txt"))) {
            List<Integer> numbers = br.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
            out: for (int i = 0; i < numbers.size(); i++) {
                for (int j = 1; j < numbers.size(); j++) {
                    int ret = findComplement(numbers, i, j, numbers.get(j), 2020 - numbers.get(i));
                    if (ret == 0) {
                        break out;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int findComplement(List<Integer> numbers, int x, int y, int val, int sum) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i == x || i == y) {
                continue;
            }
            int num = numbers.get(i);
            if (val + num == sum) {
                System.out.println(val + " + " + num + " + " + (2020 - sum) + " = 2020");
                System.out.println(val + " * " + num + " * " + (2020 - sum) + " = " + val * num * (2020 - sum));
                return 0;
            }
        }
        return -1;
    }
}
