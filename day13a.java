package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input13.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            int target = Integer.parseInt(lines.get(0));
            int busId = 0;
            int departure = Integer.MAX_VALUE;
            String[] buses = lines.get(1).split(",");
            for (int i = 0; i < buses.length; i++) {
                if (buses[i].equals("x")) continue;
                else {
                    int bus = Integer.parseInt(buses[i]);
                    if (target % bus == 0) {
                        busId = bus;
                        departure = target;
                        break;
                    } else {
                        int time = (target / bus) * bus + bus;
                        if (time < departure) {
                            departure = time;
                            busId = bus;
                        }
                    }
                }
            }
            System.out.println(busId * (departure - target));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
