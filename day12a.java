package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    private static int x = 0;
    private static int y = 0;
    private static char dir = 'E';

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input12.txt"))) {
            br.lines().map(Instruction::new).forEach(Instruction::execute);
            System.out.println(Math.abs(x) + Math.abs(y));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class Instruction {

        char cmd;
        int val;

        Instruction(String str) {
            this.cmd = str.charAt(0);
            this.val = Integer.parseInt(str.substring(1));
        }

        void execute() {
            if (cmd == 'F') cmd = dir;
            switch (cmd) {
                case 'N':
                    y += val;
                    break;
                case 'E':
                    x += val;
                    break;
                case 'S':
                    y -= val;
                    break;
                case 'W':
                    x -= val;
                    break;
                case 'L':
                    for (int i = 0; i < val / 90; i++) {
                        rotate(false);
                    }
                    break;
                case 'R':
                    for (int i = 0; i < val / 90; i++) {
                        rotate(true);
                    }
                    break;
            }
        }

        private void rotate(boolean clockwise) {
            if (dir == 'E' && !clockwise || dir == 'W' && clockwise) dir = 'N';
            else if (dir == 'S' && !clockwise || dir == 'N' && clockwise) dir = 'E';
            else if (dir == 'W' || dir == 'E') dir = 'S';
            else if (dir == 'N' || dir == 'S') dir = 'W';
        }
    }
}
