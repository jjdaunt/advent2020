package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input13.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            String[] busIds = lines.get(1).split(",");
            List<Integer> buses = new ArrayList<>();
            List<Integer> mods = new ArrayList<>();

            // It's like the Chinese Remainder Theorem, but a bit dumber because math class was too long ago.
            long product = 1;
            for (int i = 0; i < busIds.length; i++) {
                if (busIds[i].equals("x")) continue;
                else {
                    int bus = Integer.parseInt(busIds[i]);
                    buses.add(bus);
                    mods.add(-i % bus + bus);
                    product *= bus;
                }
            }
            List<Long> prodDivs = new ArrayList<>();
            List<Long> inverses = new ArrayList<>();
            for (int i = 0; i < buses.size(); i++) {
                prodDivs.add(product / buses.get(i));
                inverses.add(invert(prodDivs.get(i), buses.get(i)));
            }
            long result = 0;
            for (int i = 0; i < buses.size(); i++) {
                result += mods.get(i) * prodDivs.get(i) * inverses.get(i);
            }
            System.out.println(result % product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static long invert(long pd, int bus) {
        int x = 1;
        while (pd * x % bus != 1) x++;
        return x;
    }
}
