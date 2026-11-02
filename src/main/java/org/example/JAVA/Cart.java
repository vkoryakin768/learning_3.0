package org.example.JAVA;

import java.util.HashMap;
import java.util.Map;

public class Cart {


    private Map<Product, Integer> products = new HashMap<>();


    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Количество должно быть больше 0");
            return;
        }

        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
    }


    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }


    public int getProductCount() {
        int count = 0;
        for (int quantity : products.values()) {
            count += quantity;
        }
        return count;
    }


    public static String formatPrice(double price) {
        return String.format("%.2f руб.", price);
    }
}