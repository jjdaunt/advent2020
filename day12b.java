package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    private static int x = 0;
    private static int y = 0;
    private static int wayX = 10;
    private static int wayY = 1;

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
            switch (cmd) {
                case 'N':
                    wayY += val;
                    break;
                case 'E':
                    wayX += val;
                    break;
                case 'S':
                    wayY -= val;
                    break;
                case 'W':
                    wayX -= val;
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
                case 'F':
                    x += wayX * val;
                    y += wayY * val;
                    break;
            }
            System.out.println(cmd + "/" + val + "/" + x + "/" + y + "/" + wayX + "/" + wayY);
        }

        private void rotate(boolean clockwise) {
            if (clockwise) {
                int tmp = -wayX;
                wayX = wayY;
                wayY = tmp;
            } else {
                int tmp = -wayY;
                wayY = wayX;
                wayX = tmp;
            }
        }
    }
}
