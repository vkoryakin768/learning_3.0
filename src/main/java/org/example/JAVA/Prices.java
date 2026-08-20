package org.example.JAVA;

import java.util.List;

public class Prices {
    public static void main(String[] args) {
        List<Double> prices = List.of(150.55, 174.24, 50.55, 10.99, 132.91);
        double budget = 519.24;

        double total = 0;
        for (int i = 0; i < prices.size(); i++) {
            total += prices.get(i);
        }

        System.out.println("Общая сумма всех товаров = " + total);
        System.out.println("Бюджет = " + budget);
        if (budget >= total) {
            double reminder = budget - total;
            System.out.println("Бюджета хватает! Остаток = " + reminder);
        } else if (budget < total) {
            double shortage = total - budget;
            System.out.println("Бюджета не хватает! Нужно = " + shortage);
        }
    }
}
