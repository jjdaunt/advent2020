package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input14.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            Map<Integer, String> memory = new HashMap<>();
            String mask = "";
            for (String line : lines) {
                String[] parts = line.split(" = ");
                if (parts[0].equals("mask")) {
                    mask = parts[1];
                } else {
                    int memVal = Integer.parseInt(parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']')));
                    memory.put(memVal, mask(mask, Integer.toBinaryString(Integer.parseInt(parts[1]))));
                }
            }
            System.out.println(memory.values().stream().map(s -> Long.parseLong(s, 2)).mapToLong(Long::longValue).sum());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String mask(String mask, String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 36; i > input.length(); i--) {
            result.append('0');
        }
        result.append(input);
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) != 'X') result.replace(i, i + 1, mask.substring(i, i + 1));
        }
        return result.toString();
    }
}
