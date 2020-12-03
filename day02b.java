package com.scratch;

import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input2.txt"))) {
            System.out.println(br.lines().map(Main::isValid).filter(b -> b).count() + " passwords are valid.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isValid(String line) {
        String[] parts = line.split(" ");
        String[] minmax = parts[0].split("-");
        int min = parseInt(minmax[0]);
        int max = parseInt(minmax[1]);
        char letter = parts[1].charAt(0);
        String password = parts[2];

        return password.charAt(min - 1) == letter ^ password.charAt(max - 1) == letter;
    }
}
