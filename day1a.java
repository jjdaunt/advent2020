package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input1.txt"))) {
            List<Integer> numbers = br.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
            for (int i = 0; i < numbers.size(); i++) {
                int ret = findComplement(numbers, i, numbers.get(i), 2020);
                if (ret == 0) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int findComplement(List<Integer> numbers, int x, int val, int sum) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i == x) {
                continue;
            }
            int num = numbers.get(i);
            if (val + num == sum) {
                System.out.println(val + "+" + num + "=" + sum);
                System.out.println(val + "*" + num + "=" + val * num);
                return 0;
            }
        }
        return -1;
    }
}
