package org.example.JAVA;

public class MathHelper {
    public static int sum(int a, int b){
        return  a+b;
    }
    public static int max(int a, int b){
        return (a > b) ? a : b;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    private MathHelper() {
        throw new UnsupportedOperationException("Утилитный класс нельзя инстанцировать");
    }

}
