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
                        for (int d = 0; d < 8; d++) {
                            if (seatCheck(a, d, i, j)) {
                                occupied++;
                                break;
                            }
                        }
                        b[i][j] = occupied > 0 ? 'L' : '#';
                        break;
                    case '#':
                        for (int d = 0; d < 8; d++) {
                            if (seatCheck(a, d, i, j)) {
                                occupied++;
                                if (occupied > 4) break;
                            }
                        }
                        b[i][j] = occupied > 4 ? 'L' : '#';
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
    
    private static boolean seatCheck(char[][] a, int dir, int i, int j) {
        switch (dir) {
            case 0:
                while (i > 0 && j > 0) {
                    i--;
                    j--;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 1:
                while (i > 0) {
                    i--;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 2:
                while (i > 0 && j < a[i].length - 1) {
                    i--;
                    j++;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 3:
                while (j > 0) {
                    j--;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 4:
                while (j < a[i].length - 1) {
                    j++;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 5:
                while (i < a.length - 1 && j > 0) {
                    i++;
                    j--;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 6:
                while (i < a.length - 1) {
                    i++;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            case 7:
                while (i < a.length - 1 && j < a[i].length - 1) {
                    i++;
                    j++;
                    if (a[i][j] == '#') return true;
                    if (a[i][j] == 'L') return false;
                }
                return false;
            default:
                return false;
        }
    }
}
