package com.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\scratch\\advent\\input07.txt"))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            Map<String, Bag> colourBagMap = new HashMap<>();
            for (String line : lines) {
                String[] parts = line.split(" bags contain ");
                String root = parts[0];
                colourBagMap.putIfAbsent(root, new Bag(root));
                String[] contents = parts[1].substring(0, parts[1].length() - 1).split(", ");
                for (String leaf : contents) {
                    if (leaf.contains("other")) {
                        continue;
                    }
                    int num = Integer.parseInt(leaf.substring(0, leaf.indexOf(' ')));
                    String colour = leaf.substring(leaf.indexOf(' ') + 1, leaf.lastIndexOf(' '));
                    colourBagMap.putIfAbsent(colour, new Bag(colour));
                    colourBagMap.get(root).addBags(colourBagMap.get(colour), num);
                }
            }
            System.out.println(colourBagMap.get("shiny gold").countBags());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class Bag {
        String colour;
        Map<Bag, Integer> bagCounts = new HashMap<>();

        Bag(String colour) {
            this.colour = colour;
        }

        void addBags(Bag bag, int num) {
            this.bagCounts.put(bag, num);
        }

        boolean containsBag(String colour) {
            if (this.colour.equals(colour)) {
                return false;
            }
            return bagCounts.keySet().stream().anyMatch(bag -> bag.colour.equals(colour) || bag.containsBag(colour));
        }

        int countBags() {
            return bagCounts.entrySet().stream()
                    .map(e -> e.getValue() + e.getValue() * e.getKey().countBags())
                    .mapToInt(Integer::intValue).sum();
        }
    }
}
