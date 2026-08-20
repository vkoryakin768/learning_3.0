package org.example.JAVA;

import java.util.List;

public class Fruits {
    public static void main(String[] args) {
        List<String> fruits = List.of("Яблоко", "Банан", "Киви", "Груша", "Апельсин");
        for (int i = 0; i < fruits.toArray().length; i++) {
            System.out.println((i + 1) + ". " + fruits.get(i));
        }
    }
}
