package com.scratch;

import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input04.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            lines.add("");
            int count = 0;
            int fields = 0;
            for (String line : lines) {
                if (line.length() == 0) {
                    if (fields == 7) {
                        count++;
                    }
                    fields = 0;
                } else {
                    String[] entries = line.split(" ");
                    for (String entry : entries) {
                        String[] sides = entry.split(":");
                        if (isValidEntry(sides[0], sides[1])) {
                            fields++;
                        }
                    }
                }
            }
            System.out.println(count + " valid passports detected.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isValidEntry(String code, String value) {
        if (code.isEmpty() || value.isEmpty()) {
            return false;
        }
        switch (code) {
            case "byr":
                return (value.length() == 4 && parseInt(value) < 2003 && parseInt(value) > 1919);
            case "iyr":
                return (value.length() == 4 && parseInt(value) < 2021 && parseInt(value) > 2009);
            case "eyr":
                return (value.length() == 4 && parseInt(value) < 2031 && parseInt(value) > 2019);
            case "hgt":
                if (value.length() < 4) {
                    return false;
                }
                int num = parseInt(value.substring(0, value.length() - 2));
                String unit = value.substring(value.length() - 2);
                return (unit.equals("in") && num > 58 && num < 77) || (unit.equals("cm") && num > 149 && num < 194);
            case "hcl":
                return value.matches("#[0-9|a-f]{6}");
            case "ecl":
                return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
            case "pid":
                return value.matches("[0-9]{9}");
            default:
                return false;
        }
    }
}
