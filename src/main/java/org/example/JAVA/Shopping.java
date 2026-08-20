package org.example.JAVA;

import java.util.List;

public class Shopping {

        public static void main(String[] args) {
            List<Double> prices = List.of(199.99, 549.50, 89.00, 1299.90, 349.99);
            double budget = 500.00;
            double initialBudget = budget;

            int boughtCount = 0;
            int i = 0;

            while (i < prices.size() && budget >= prices.get(i)) {
                budget -= prices.get(i);
                boughtCount++;
                i++;
            }

            double totalSpent = initialBudget - budget;
            int notBought = prices.size() - boughtCount;

            System.out.println("Куплено: " + boughtCount + " товара на сумму " + totalSpent);
            System.out.println("Остаток бюджета: " + budget);
            System.out.println("Не куплено: " + notBought + " товара");
        }
    }

