package org.example;

import org.example.JAVA.*;


public class Main {

    public static void main(String[] args) {

        /*Person person = new Person(25, "Baskov", "Nikolay");


        person.introduce();


        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwnerName("Vladislav");
        bankAccount.setBalance(0);
        System.out.println("Владелец аккаунта: " + bankAccount.getOwnerName() + ". Ваш баланс: " + bankAccount.getBalance());

        int sumResult = MathHelper.sum(10, 5);
        System.out.println("Сумма 10 + 5 = " + sumResult);

        int maxResult = MathHelper.max(10, 5);
        System.out.println("Максимум из 10 и 5 = " + maxResult);

        boolean isEvenResult = MathHelper.isEven(10);
        System.out.println("Число 10 чётное? " + isEvenResult);

        Visitor visitor1 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor2 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor3 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor4 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor5 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        System.out.println(visitor1.getTotalVisitors());
        System.out.println(visitor3.getTotalVisitors());
        System.out.println(visitor5.getTotalVisitors());*/
        //Переменная totalVisitors статичная и не привязана к конкретному объекту
        //При создании нового объекта счетчик увеличивается на 1.
        // При вызове статичного сеттера так же выведено конечное значение счетчика всего класса,
        // независимо от какого обекта оно вызывается


        Product bread = new Product("Хлеб", 50.0);
        Product milk = new Product("Молоко", 80.0);
        Product cheese = new Product("Сыр", 300.0);
        Product apples = new Product("Яблоки", 120.0);


        Cart cart = new Cart();


        cart.addProduct(bread, 2);
        cart.addProduct(milk, 1);
        cart.addProduct(cheese, 1);
        cart.addProduct(bread, 3);
        cart.addProduct(apples, 4);


        System.out.println("Общее количество товаров: " + cart.getProductCount());
        System.out.println("Общая стоимость: " + Cart.formatPrice(cart.getTotalPrice()));
    }


}
