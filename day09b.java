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
            int len = numbers.size();
            long target = findTarget(numbers); //133015568
            long[] sums = new long[len];
            int[] removedAt = new int[len];
            for (int i = 0; i < len; i++) {
                long num = numbers.get(i);
                removedAt[i] = i;
                for (int j = 0; j < i; j++) {
                    sums[j] += num;
                    while (sums[j] > target) {
                        sums[j] -= numbers.get(removedAt[j]);
                        removedAt[j]++;
                    }
                    if (sums[j] == target) {
                        System.out.println(findWeakness(numbers, removedAt[j] + 1, i));
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static long findTarget(List<Long> numbers) {
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
                return num;
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
        return -1;
    }

    private static long findWeakness(List<Long> numbers, int x, int y) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = x; i <= y; i++) {
            min = Long.min(min, numbers.get(i));
            max = Long.max(max, numbers.get(i));
        }
        return min + max;
    }
}
