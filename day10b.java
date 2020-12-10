package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer, Long> pathways = new HashMap<>();
    static List<Integer> lines = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input10.txt"))) {
            lines.addAll(br.lines().map(Integer::parseInt).sorted().collect(Collectors.toList()));
            pathways.put(0, 1L);
            System.out.println(findPathways(lines.get(lines.size() - 1)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static long findPathways(int val) {
        if (pathways.containsKey(val)) {
            return pathways.get(val);
        } else if (!lines.contains(val)) {
            return 0;
        } else {
            pathways.put(val, findPathways(val - 1) + findPathways(val - 2) + findPathways(val - 3));
            return pathways.get(val);
        }
    }
}
