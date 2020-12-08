package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int count = 0;
    private static int pos = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input08.txt"))) {
            List<Instruction> instructions = br.lines().map(Main::convert).collect(Collectors.toList());
            while (pos != -1) {
                instructions.get(pos).execute();
            }
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class Instruction {
        String command;
        int value;
        boolean executed;

        Instruction(String command, int value) {
            this.command = command;
            this.value = value;
            this.executed = false;
        }

        void execute() {
            if (this.executed) {
                pos = -1;
                return;
            } else {
                switch (command) {
                    case "acc":
                        count += this.value;
                        pos++;
                        break;
                    case "jmp":
                        pos += this.value;
                        break;
                    case "nop":
                        pos++;
                        break;
                    default:
                        pos = -1;
                        return;
                }
                this.executed = true;
            }
        }
    }

    private static Instruction convert(String line) {
        String[] parts = line.split(" ");
        parts[1] = parts[1].replace("+", "");
        return new Instruction(parts[0], Integer.parseInt(parts[1]));
    }
}
