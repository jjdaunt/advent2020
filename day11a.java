package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input11.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            char[][] a = new char[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                char[] row = lines.get(i).toCharArray();
                a[i] = row;
            }
            char[][] b = execute(a);
            while (diff(a, b)) {
                a = b;
                b = execute(a);
            }
            System.out.println(count(b));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static char[][] execute(char[][] a) {
        char[][] b = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            b[i] = new char[a[i].length];
            for (int j = 0; j < a[i].length; j++) {
                int occupied = 0;
                switch(a[i][j]) {
                    case '.':
                        b[i][j] = '.';
                        break;
                    case 'L':
                        if (i > 0 && j > 0 && a[i-1][j-1] == '#') occupied++;
                        if (occupied == 0 && i > 0 && a[i-1][j] == '#') occupied++;
                        if (occupied == 0 && i > 0 && j < a[i-1].length - 1 && a[i-1][j+1] == '#') occupied++;
                        if (occupied == 0 && j > 0 && a[i][j-1] == '#') occupied++;
                        if (occupied == 0 && j < a[i].length - 1 && a[i][j+1] == '#') occupied++;
                        if (occupied == 0 && i < a.length - 1 && j > 0 && a[i+1][j-1] == '#') occupied++;
                        if (occupied == 0 && i < a.length - 1 && a[i+1][j] == '#') occupied++;
                        if (occupied == 0 && i < a.length - 1 && j < a[i+1].length - 1 && a[i+1][j+1] == '#') occupied++;
                        b[i][j] = occupied > 0 ? 'L' : '#';
                        break;
                    case '#':
                        if (i > 0 && j > 0 && a[i-1][j-1] == '#') occupied++;
                        if (i > 0 && a[i-1][j] == '#') occupied++;
                        if (i > 0 && j < a[i-1].length - 1 && a[i-1][j+1] == '#') occupied++;
                        if (j > 0 && a[i][j-1] == '#') occupied++;
                        if (j < a[i].length - 1 && a[i][j+1] == '#') occupied++;
                        if (i < a.length - 1 && j > 0 && a[i+1][j-1] == '#') occupied++;
                        if (i < a.length - 1 && a[i+1][j] == '#') occupied++;
                        if (i < a.length - 1 && j < a[i+1].length - 1 && a[i+1][j+1] == '#') occupied++;
                        b[i][j] = occupied > 3 ? 'L' : '#';
                        break;
                    default:
                        break;
                }
            }
        }
        return b;
    }

    private static boolean diff(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int count(char[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}
